package com.example.pma2.Interfaces;

import com.example.pma2.Enum.FragmentEnum;

public interface GetDataInterface {
    void viewReadyForData(FragmentEnum type);
    void viewReturningData(Object object, FragmentEnum type);
}
