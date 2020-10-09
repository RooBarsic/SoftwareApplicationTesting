package domain;

import com.company.domain.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanTest {

    @Test
    public void humanPowerNotLessThan10() {
        final Human human = new Human("Aziz", "Saidov", Collections.emptyList());
        assertTrue("health <= 0", human.isAlive());
        assertTrue("power < 10", human.getPower() >= 10);
        final Human human2 = new Human("Ahmad", "Mansurov", Collections.emptyList());
        assertTrue("health <= 0", human2.isAlive());
        assertTrue("power < 10", human2.getPower() >= 10);

        int i = 0;
        while (human.isAlive() && human2.isAlive()) {
            if (i++ % 2 == 0)
                human2.gotHitFrom(human);
            else
                human.gotHitFrom(human2);
            assertTrue("power < 10", human.getPower() >= 10);
            assertTrue("power < 10", human2.getPower() >= 10);
        }
    }

    @Test
    public void humanCantWearTwoIdenticalClothes() {
        final Clothes blackShirt = new Clothes(ClothesType.SHIRT, Color.BLACK, null);
        final Clothes whiteShirt = new Clothes(ClothesType.SHIRT, Color.WHITE, null);
        final Human human = new Human("Azula", "Ozai", Arrays.asList(blackShirt, whiteShirt));

        assertEquals(human.showClothes().size(), 1);
        assertEquals(human.showClothes().get(0), whiteShirt);

        final Clothes whiteItmoShirt = new Clothes(ClothesType.SHIRT, Color.WHITE, Brand.ITMO_UNIVERSITY);
        human.addClothes(whiteItmoShirt);
        assertEquals(human.showClothes().size(), 1);
        assertEquals(human.showClothes().get(0), whiteItmoShirt);

        human.removeClothes(ClothesType.SHIRT);
        assertEquals(human.showClothes().size(), 0);
    }
}
