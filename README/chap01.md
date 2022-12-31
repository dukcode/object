# [chap01] 객체, 설계

마틴 파울러에 따르면 소프트웨어 모듈에는 3가지 목적이 있다.

- 재대로 동작
- 변경에 용이
- 이해하기 쉬워야 함

다음의 코드는 다음의 역할을 한다. 초대장이 있는 관객에게는 초대장을 티켓으로 교환해주고, 초대장이 없는 관객에게는 돈을 받고 티켓을 판매한다. 다음의 코드는 재대로 동작하기는 하지만 변경에 용이하지 않고, 이해하기 쉽지 않다. 그 이유를 알아보고 코드를 개선해 보자.

```java
public class Theater {  
    private TicketSeller ticketSeller;  
  
    public Theater(TicketSeller ticketSeller) {  
        this.ticketSeller = ticketSeller;  
    }  
  
    public void enter(Audience audience) {  
        if (audience.getBag().hasInvitation()) {  
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();  
            audience.getBag().setTicket(ticket);  
        } else {  
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();  
            audience.getBag().minusAmount(ticket.getFee());  
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());  
            audience.getBag().setTicket(ticket);  
        }  
    }  
}
```

## 원인 파악

### 이해하기 어려운 이유

#### 예상을 빗나가는 코드 (주체가 누구인가)

`Audience`와 `TicketSeller`가 `Theater`의 통제를 받고 있다. `Theater`가 허락도 없이 `Audience`의 `Bag`에 **마음대로** 접근하여 돈을 빼가고, `TicketSeller`의 `TicketOffice`에 허락도 없이 접근해 마음대로 티켓과 돈을 만진다.

이해 가능한 코드는 예상이 가능해야 한다. 일반적으로 가방을 여는 주체는 관람객이고, 티켓 오피스의 돈을 만지는 주체는 티켓 판매원이다. 하지만 현재는 소극장이 이 역할을 모두 수행하고 있다.

이 코드는 **우리의 상식과는 너무나도 다르게 동작**하기 때문에 코드를 읽는 사람과 제대로 의사소통 할 수 없다.

#### 세부적인 내용을 다 알고 있어야 함

위의 코드를 이해하려면 여러 세부적 내용을 알고 있어야 한다. 예를 들어 `Audience`가 `Bag`을 가지고 있고 여기에 티켓과 현금이 들어있으며 `TicketSeller`의 오피스를 에서 티켓과 돈이 관리된다는 사실 등이다.

이 코드는 하나의 클래스나 메서드에 너무 많은 세부사항을 다루기 때문에 코드를 작성하는 사람이나 읽는 사람 모두에게 큰 부담을 준다.

### 변경에 취약한 이유

#### 과한 의존성(dependency)

현재 `Theater`클래스는 `TicketSeller`와 연관관계를 가지고 있음과 동시에 `Audience`, `Bag`, `TicketOffice`, `Ticket`, `Invitation`과 모두 의존관계를 가지고 있다.

따라서 어떤 클래스에 변경사항이 생기면(ex. 관람객이 가방을 안들고, 신용카드 사용) 변경사항이 생긴 해당 클래스 외에도 `Theater`클래스의 `enter()`메서드도 수정이 이루어 져야 한다.

의존성이 과한 경우를 가르켜 **결합도(coupling)**가 높다고 한다. 반대로 객체가 합리적 수준의 의존성을 가질 경우 결합도가 낮다고 말한다.

의존성이 생긴다는 것은 **객체들이 협력**함을 의미하기도 하지만, **변경에 취약해**지는 것을 의미하기도 한다. 따라서 우리의 목표는 **필요한 최소한의 의존성만 유지하고 불필요한 의존성을 제거함에 있다.** 즉, 객체 사이의 결합도를 낮추어 변경이 용이한 설계를 만드는 것이다.

## 개선하기

- 관람객과 판매원이 자신의 일을 스스로 처리하도록 변경한다.
	- 관람객과 판매원이 자신의 일을 스스로 처리한다는 직관을 살릴 수 있다.
	- `Theater`가 관람객과 판매원의 내부 정보를 몰라도 되어서 결합도가 떨어진다. 즉, 변경이 용이한 코드를 작성할 수 있다.

즉, 관람객과 판매원을 **자율적인 존재**로 만든다.

`TicketOffice`에 접근하는 모든 코드를 `TicketSeller` 내부로 숨긴다.

기존 `Theater` 클래스

```java
public class Theater {  
    private TicketSeller ticketSeller;  
  
    public Theater(TicketSeller ticketSeller) {  
        this.ticketSeller = ticketSeller;  
    }  
  
    public void enter(Audience audience) {  
        if (audience.getBag().hasInvitation()) {  
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();  
            audience.getBag().setTicket(ticket);  
        } else {  
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();  
            audience.getBag().minusAmount(ticket.getFee());  
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());  
            audience.getBag().setTicket(ticket);  
        }  
    }  
}
```

변경된 `Theater` 클래스

```java
public class Theater {  
    private TicketSeller ticketSeller;  
  
    public Theater(TicketSeller ticketSeller) {  
        this.ticketSeller = ticketSeller;  
    }  
  
    public void enter(Audience audience) {  
        ticketSeller.sellTo(audience);  
    }  
}
```

위처럼 티켓 판매에 대한 역할을 `TicketSeller`에게로 변경했다. 이로써 `Theater`클래스는 `TicketOffice`와 `Ticket`, `Audience` 및 `Bag`에 대한 의존성을 줄여 결합도를 떨어뜨릴 수 있게 되었다.

`Theater`클래스는 `TicketSeller`의 인터페이스에만 의존한다. 객체를 인터페이스와 구현으로 나누고 인터페이스만을 공개하는 것은 객체 사이의 결합도를 낮추고 변경하기 쉬운 코드를 작성하기 쉽게 한다.

이제 `TicketSeller` 클래스를 살펴보자.

기존 `TicketSeller`클래스

```java
public class TicketSeller {  
    private TicketOffice ticketOffice;  
  
    public TicketSeller(TicketOffice ticketOffice) {  
        this.ticketOffice = ticketOffice;  
    }  
  
    public TicketOffice getTicketOffice() {  
        return ticketOffice;  
    }  
}
```

변경된 `TicketSeller` 클래스

```java
public class TicketSeller {  
    private TicketOffice ticketOffice;  
  
    public TicketSeller(TicketOffice ticketOffice) {  
        this.ticketOffice = ticketOffice;  
    }  
  
    public void sellTo(Audience audience) {  
        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));  
    }  
}
```

결과적으로 `TicketOffice`에 대한 접근은 오직 `TicketSeller` 안에만 존재하게 되었다. 이처럼 개념적이 물리적으로 객체 내부의 세부적인 사항을 감추는 것을 **캡슐화**라고 한다. 캡슐화를 통해 객체 내부로의 접근을 제한하면 객체와 객체 사이의 결합도를 낮출 수 있게 된다. 이로써 설계 변경을 더 쉽게 할 수 있게된다.

`Audience`와 `Bag`에도 같은 원칙을 적용해 보자.

기존 `Audience` 클래스

```java
public class Audience {  
    private Bag bag;  
  
    public Audience(Bag bag) {  
        this.bag = bag;  
    }  
  
    public Bag getBag() {  
        return bag;  
    }  
}
```

변경된 `Audience` 클래스

```java
public class Audience {  
    private Bag bag;  
  
    public Audience(Bag bag) {  
        this.bag = bag;  
    }  
  
    public Long buy(Ticket ticket) {  
        if (bag.hasInvitation()) {  
            bag.setTicket(ticket);  
            return 0L;  
        } else {  
            bag.setTicket(ticket);  
            bag.minusAmount(ticket.getFee());  
            return ticket.getFee();  
        }  
    }  
}
```

기존 `Bag` 클래스

```java
public class Bag {  
    private Long amount;  
    private Invitation invitation;  
    private Ticket ticket;  
  
    public Bag(long amount) {  
        this(null, amount);  
    }  
  
    public Bag(Invitation invitation, long amount) {  
        this.invitation = invitation;  
        this.amount = amount;  
    }  
  
    public boolean hasInvitation() {  
        return invitation != null;  
    }  
  
    public boolean hasTicket() {  
        return ticket != null;  
    }  
  
    public void setTicket(Ticket ticket) {  
        this.ticket = ticket;  
    }  
  
    public void minusAmount(Long amount) {  
        this.amount -= amount;  
    }  
  
    public void plusAmount(Long amount) {  
        this.amount += amount;  
    }  
}
```

변경된 `Bag` 클래스

```java  
public class Bag {  
    private Long amount;  
    private Ticket ticket;  
    private Invitation invitation;  
  
    public Long hold(Ticket ticket) {  
        if (hasInvitation()) {  
            setTicket(ticket);  
            return 0L;  
        } else {  
            setTicket(ticket);  
            minusAmount(ticket.getFee());  
            return ticket.getFee();  
        }  
    }  
  
    private void setTicket(Ticket ticket) {  
        this.ticket = ticket;  
    }  
  
    private boolean hasInvitation() {  
        return invitation != null;  
    }  
  
    private void minusAmount(Long amount) {  
        this.amount -= amount; 
}
```

이로서 `Bag`에 대한 정보를 `Audience`안에 캡슐화 시켜 결합도를 낮추고 변경이 용이한 코드로 변경되었다.

> 자신과 밀접하게 연관된 작업만을 수행하고 직접적 연관성이 없는 작업은 다른 객체에게 위임하는 것을 가르켜 **응집도**가 높다고 한다.
 
자신의 데이터를 스스로 처리하는 지율적인 객체를 만들면 결합도를 낮출 수 있을뿐더러 응집도를 높일 수 있다.

### 트레이드 오프

하지만 스스로 자신의 데이터를 책임지는 **자율성**을 높이는 것이 항상 결합도를 낮추는 것은 아니다. 특정 객체의 자율성을 높이는 행위가 전체 설계에서의 결합도에 영향을 주지 못하고나 심지어는 증가시킬 수 있다. 이럴 때는 적절한 선택을 통해 균형있는 설계를 만들어야 할 것이다.

