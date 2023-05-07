package hello.proxy.pureproxy.concreteproxy.code;

import hello.proxy.pureproxy.concreteproxy.ConcreteLogic;

public class ConcreteClient {

    private ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute() {
        concreteLogic.operation();
    }
}
