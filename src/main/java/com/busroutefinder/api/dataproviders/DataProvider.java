package com.busroutefinder.api.dataproviders;

import java.io.IOException;
import java.util.List;

public interface DataProvider {
    List<List<Integer>> fetch() throws IOException;
}
