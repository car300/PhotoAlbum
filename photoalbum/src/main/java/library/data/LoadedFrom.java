package library.data;

public enum LoadedFrom {
    NETWORK,
    DISC,
    DISC_CACHE,
    MEMORY_CACHE;

    private LoadedFrom() {
    }
}