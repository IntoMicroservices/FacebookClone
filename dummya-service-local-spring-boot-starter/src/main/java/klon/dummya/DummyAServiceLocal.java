package klon.dummya;

import klon.dummyb.DummyBService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DummyAServiceLocal implements DummyAService {

    private final DummyBService dummyBService;

    @Override
    public String getAB() {
        return "A" + dummyBService.getB();
    }
}
