package strategy.creationStrategy;

import customClasses.Barrel;
import customClasses.enums.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarrelCreationStrategy implements CreationStrategy<Barrel> {
    private static final Random rnd = new Random();
    private static final int[] volumes = {100, 200, 300, 400, 500, 600, 700, 800, 900};
    private static final String[] storageMaterials = {"milk", "vegetables", "soap", "salt", "sugar", "beer"};

    @Override
    public List<Barrel> create(int count) {
        List<Barrel> barrels = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            barrels.add(Barrel.builder()
                    .material(Material.values()[rnd.nextInt(Material.values().length)])
                    .volume(volumes[rnd.nextInt(volumes.length)])
                    .storageMaterial(storageMaterials[rnd.nextInt(storageMaterials.length)])
                    .build());
        }
        return barrels;
    }
}
