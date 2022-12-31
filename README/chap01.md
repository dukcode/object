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

