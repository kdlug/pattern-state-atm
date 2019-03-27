package com.github.atm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.atm.ATMMachine;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ATMMachineTest {
    ATMMachine atmMachine;

    @Test
    void requestCashWithValidPin_ExpectedNoCardState() {
        atmMachine = new ATMMachine();

        atmMachine.insertCard();
        atmMachine.insertPin(1234);
        atmMachine.requestCash(2000);

        Assertions.assertEquals(atmMachine.getNoCashState(), atmMachine.atmState);
    }

    @Test
    void ejectCardWhenNotInserted_ExpectedNoCardState() {
        atmMachine = new ATMMachine();

        atmMachine.ejectCard();

        Assertions.assertEquals(atmMachine.getNoCardState(), atmMachine.atmState);
    }

    @Test
    void requestCashWithValidPin_ExpectedOutOfMoneyState() {
        atmMachine = new ATMMachine();

        atmMachine.insertCard();
        atmMachine.insertPin(1234);
        atmMachine.requestCash(2000);

        atmMachine.insertCard();
        atmMachine.insertPin(1234);
        atmMachine.requestCash(2);

        Assertions.assertEquals(atmMachine.getNoCashState(), atmMachine.atmState);
    }

    @Test
    void requestCashWithInvalidPin_ExpectedNoCardState() {
        atmMachine = new ATMMachine();

        atmMachine.insertCard();
        atmMachine.insertPin(12345);

        Assertions.assertEquals(atmMachine.getNoCardState(), atmMachine.atmState);
    }

}