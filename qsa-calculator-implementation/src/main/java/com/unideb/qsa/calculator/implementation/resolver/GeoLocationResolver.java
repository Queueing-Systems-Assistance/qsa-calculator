package com.unideb.qsa.calculator.implementation.resolver;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

/**
 * Default GEO location resolver with GeoIP2 database.
 */
@Component
public class GeoLocationResolver {

    private static final Logger LOG = LoggerFactory.getLogger(GeoLocationResolver.class);
    private static final String FAILED_TO_RESOLVE_COUNTRY = "Failed to resolve country";

    @Autowired(required = false)
    private DatabaseReader databaseReader;

    /**
     * Resolves the country based on the IP address.
     *
     * @param ipAddress IP address
     * @return country iso code
     */
    public Optional<String> resolveCountryIsoCode(InetAddress ipAddress) {
        Optional<String> result = Optional.empty();
        try {
            result = Optional.ofNullable(resolveCountry(ipAddress).getIsoCode());
        } catch (RuntimeException | IOException | GeoIp2Exception e) {
            LOG.error(FAILED_TO_RESOLVE_COUNTRY, e);
        }
        return result;
    }

    private Country resolveCountry(InetAddress ipAddress) throws IOException, GeoIp2Exception {
        CountryResponse response = databaseReader.country(ipAddress);
        return response.getCountry();
    }
}
