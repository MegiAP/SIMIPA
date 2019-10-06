package com.vincode.simipa.util;

import com.vincode.simipa.model.Service;

import java.util.ArrayList;

public class TestDataService {

    private static String[][]dataTest = new String[][]{
            {"Surat Keterangan Aktif Kuliah", "27-07-2019", String.valueOf(0)},
            {"Form Beasiswa", "27-07-2019", String.valueOf(1)}
    };

    public static ArrayList<Service> getListService(){
        ArrayList<Service> listService = new ArrayList<>();
        for (String[] mData: dataTest){
            Service service = new Service();
            service.setForm(mData[0]);
            service.setDate(mData[1]);
            service.setState(Integer.parseInt(mData[2]));
            listService.add(service);
        }

        return listService;
    }
}
