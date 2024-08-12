package customClasses;

import customClasses.comparators.*;
import enums.Material;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Barrel implements Comparable<Barrel>, Serializable {
    private final int volume;
    private final String storedMaterial;
    private final Material material;

    private static final Comparator<Barrel> volumeComparator = new BarrelVolumeComparator();
    private static final Comparator<Barrel> materialComparator = new BarrelMaterialComparator();
    private static final Comparator<Barrel> storedComparator = new BarrelStoredMaterialComparator();

    private Barrel(int volume, String storedMaterial, Material material) {
        this.volume = volume;
        this.storedMaterial = storedMaterial;
        this.material = material;
    }

    public int getVolume() {
        return volume;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Barrel barrel = (Barrel) object;
        return volume == barrel.volume && Objects.equals(storedMaterial, barrel.storedMaterial) && material == barrel.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, storedMaterial, material);
    }

    @Override
    public String toString() {
        return "Barrel{" + "volume=" + volume + ", storedMaterial='" + storedMaterial + '\'' + ", material=" + material + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    // по объему
    @Override
    public int compareTo(Barrel o) {
        return volumeComparator.thenComparing(materialComparator).thenComparing(storedComparator).compare(this, o);
    }

    public static class Builder {
        private int volume;
        private String storedMaterial;
        private Material material;

        public Builder volume(int volume) {
            this.volume = volume;
            return this;
        }

        public Builder storageMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder material(Material material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(volume, storedMaterial, material);
        }
    }
}
