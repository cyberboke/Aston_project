package customClasses.factory.ramdom;

import customClasses.Barrel;
import enums.Material;

import java.util.Random;

public class BarrelFactory implements RandomCreatable<Barrel> {

    private static final Random rnd = new Random();
    private static final int[] VOLUMES = {100, 200, 300, 400, 500, 600, 700, 800, 900};
    private static final String[] STORED_MATERIALS = {"milk", "vegetables", "soap", "salt", "sugar", "beer"};

    @Override
    public Barrel createRandom() {
        return Barrel.builder()
                .material(Material.values()[rnd.nextInt(Material.values().length)])
                .volume(VOLUMES[rnd.nextInt(VOLUMES.length)])
                .storageMaterial(STORED_MATERIALS[rnd.nextInt(STORED_MATERIALS.length)])
                .build();
    }
}
