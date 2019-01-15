package com.nnniu.bh.ch8;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.cfg.spi.DomainDataRegionBuildingContext;
import org.hibernate.cache.cfg.spi.DomainDataRegionConfig;
import org.hibernate.cache.spi.DomainDataRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class MyRegionFactoryImpl implements RegionFactory {
	
	private static Logger logger = LogManager.getLogger(Main.class);

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		logger.debug("11111111111");
	}

	@Override
	public DomainDataRegion buildDomainDataRegion(DomainDataRegionConfig arg0, DomainDataRegionBuildingContext arg1) {
		// TODO Auto-generated method stub
		logger.debug("22222222222");
		return null;
	}

	@Override
	public QueryResultsRegion buildQueryResultsRegion(String arg0, SessionFactoryImplementor arg1) {
		// TODO Auto-generated method stub
		logger.debug("333333333");
		return null;
	}

	@Override
	public TimestampsRegion buildTimestampsRegion(String arg0, SessionFactoryImplementor arg1) {
		// TODO Auto-generated method stub
		logger.debug("44444444");
		return null;
	}

	@Override
	public AccessType getDefaultAccessType() {
		// TODO Auto-generated method stub
		logger.debug("5555555");
		return null;
	}

	@Override
	public boolean isMinimalPutsEnabledByDefault() {
		// TODO Auto-generated method stub
		logger.debug("66666666");
		return false;
	}

	@Override
	public long nextTimestamp() {
		// TODO Auto-generated method stub
		logger.debug("7777777777");
		return 0;
	}

	@Override
	public String qualify(String arg0) {
		// TODO Auto-generated method stub
		logger.debug("88888888888");
		return null;
	}

	@Override
	public void start(SessionFactoryOptions arg0, Map arg1) throws CacheException {
		// TODO Auto-generated method stub
		logger.debug("9999999999");
	}

}
