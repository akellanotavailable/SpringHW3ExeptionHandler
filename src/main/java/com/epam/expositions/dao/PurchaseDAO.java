package com.epam.expositions.dao;

import com.epam.expositions.entity.Purchase;

import java.util.List;

public interface PurchaseDAO extends GenericDAO<Purchase, Long> {
    public List<Purchase> findByExpositionId(Long id);
    public List<Purchase> findByUserId(Long id);
    public boolean deleteByExpositionId(Long id);
    public boolean deleteByUserId(Long id);
}
