package br.ufrj.ic.utils;

public class Metrics {
    private final Long duration;

    private final char unit;

    public Metrics(long start, long end, char unit) {
        this.duration = end - start;
        this.unit = unit;
    }

    public Long getDuration() {
        return duration;
    }

    public Number getDuration(char unit) {
        switch (unit) {
            case 's' -> {
                return getDurationInSecond();
            }
            case 'm' -> {
                return getDurationInMilli();
            }
            default -> {
                return getDuration();
            }
        }
    }

    private Double getDurationInMilli() {
        return getDuration() / 1000000.0;
    }

    private Double getDurationInSecond() {
        return getDuration() / 1000000000.0;
    }

    public void print() {
        switch (unit) {
            case 's' -> System.out.println("Duration: " + getDuration('s') + "s" + "\n");
            case 'm' -> System.out.println("Duration: " + getDuration('m') + "ms" + "\n");
            default -> System.out.println("Duration: " + getDuration() + "ns" + "\n");
        }
    }
}
