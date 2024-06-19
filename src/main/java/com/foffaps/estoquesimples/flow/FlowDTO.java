package com.foffaps.estoquesimples.flow;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.entry.Entry;
import com.foffaps.estoquesimples.items.exit.Exit;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import lombok.Data;

@Data
public class FlowDTO {

    private Items item;
    private Supplier supplier;
    private Entry entry;
    private Exit exit;

}
