package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(JUnitQuickcheck.class) public class KarumiHQProperties {
    /*@Property public void thereIsAlwaysMoreThanTwoMaxibons(String name, int numberOfMaxibons){
        System.out.println("-------->" + numberOfMaxibons);
        KarumiHQs office = new KarumiHQs();
        Developer developer = new Developer(name, numberOfMaxibons);
        office.openFridge(developer);
        assertTrue(office.getMaxibonsLeft()>2);
    }*/

    @Mock Chat chat;

    @Before public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Property public void thereIsAlwaysMoreThanTwoMaxibons2(@From(DevelopersGenerator.class)Developer developer){
        System.out.println("-------->" + developer.getNumberOfMaxibonsToGrab());
        KarumiHQs office = new KarumiHQs();
        office.openFridge(developer);
        assertTrue(office.getMaxibonsLeft()>2);
    }

    @Property public void ifSomeKarumiesGoToTheKitchenThenumberOfMaxibonsCanNotBeLowerThanTwo(
        List<@From(KarumiesGenerator.class) Developer> developers){
            System.out.println("-------->" + developers.size());
            KarumiHQs office = new KarumiHQs();
            office.openFridge(developers);
            assertTrue(office.getMaxibonsLeft()>2);
    }

    @Property public void ifTheIceCreamAreLessThanTwoTheMessageIsSent(
            @From(HungryDevelopersGenerator.class) Developer developer){
        KarumiHQs office = new KarumiHQs(chat);
        office.openFridge(developer);
        verify(chat).sendMessage(anyString());
    }

    @Property public void ifTheIceCreamAreLessThanTwoTheMessageIsSent2(
            List<@From(HungryDevelopersGenerator.class) Developer> developers){
        KarumiHQs office = new KarumiHQs(chat);
        office.openFridge(developers);
        if (developers.size()>0){
            verify(chat, atLeastOnce()).sendMessage(anyString());
        }
    }

}
