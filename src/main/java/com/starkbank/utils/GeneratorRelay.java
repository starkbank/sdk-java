package com.starkbank.utils;
import com.starkcore.utils.SubResource;
import java.util.Iterator;

public class GeneratorRelay<T extends SubResource> extends com.starkbank.utils.Generator<T> {
    private final com.starkcore.utils.Generator<T> coreGenerator;

    public GeneratorRelay(com.starkcore.utils.Generator<T> coreGenerator) {
        this.coreGenerator = coreGenerator;
    }

    @Override
    public void run() throws Exception {
        coreGenerator.run();
    }

    @Override
    public Iterator<T> iterator() {
        return coreGenerator.iterator();
    }
}
