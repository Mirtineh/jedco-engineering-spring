package com.jedco.jedcoengineeringspring.services;

import com.jedco.jedcoengineeringspring.rest.response.LongLatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.osgeo.proj4j.*;
@Slf4j
@Service
@RequiredArgsConstructor
public class UTMtoLangLatService {
    public LongLatResponse convertUTMToLangLat(String northingString, String eastingString){
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem utmCrs = crsFactory.createFromName("EPSG:32636");
        CoordinateReferenceSystem wgs84Crs = crsFactory.createFromParameters("WGS84", "+proj=longlat +datum=WGS84 +no_defs"); // WGS84 CRS// UTM Zone 36N
        // Create a CoordinateTransformFactory
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        double easting = Double.parseDouble(eastingString);
        double northing = Double.parseDouble(northingString);
        ProjCoordinate utmCoord = new ProjCoordinate(easting, northing);
        ProjCoordinate latLngCoord = new ProjCoordinate();
        // Create a CoordinateTransform instance
        CoordinateTransform transform = ctFactory.createTransform(utmCrs, wgs84Crs);

        // Perform the transformation
        transform.transform(utmCoord, latLngCoord);
        return new LongLatResponse(Double.toString(latLngCoord.x),Double.toString(latLngCoord.y));
    }
    public LongLatResponse convertUTMToLangLat(double northing, double easting){
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem utmCrs = crsFactory.createFromName("EPSG:32636");
        CoordinateReferenceSystem wgs84Crs = crsFactory.createFromParameters("WGS84", "+proj=longlat +datum=WGS84 +no_defs"); // WGS84 CRS// UTM Zone 36N
        // Create a CoordinateTransformFactory
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        ProjCoordinate utmCoord = new ProjCoordinate(easting, northing);
        ProjCoordinate latLngCoord = new ProjCoordinate();
        // Create a CoordinateTransform instance
        CoordinateTransform transform = ctFactory.createTransform(utmCrs, wgs84Crs);

        // Perform the transformation
        transform.transform(utmCoord, latLngCoord);
        return new LongLatResponse(Double.toString(latLngCoord.x),Double.toString(latLngCoord.y));
    }
}
