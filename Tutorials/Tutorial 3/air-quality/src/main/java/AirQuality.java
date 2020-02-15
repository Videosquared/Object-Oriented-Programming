public class AirQuality {
    /**
     * Constant for data that is Not Available (N/A)
     */
    public static final double NA = -1;

    /**
     * getDAQI
     * @param ozone running 8 hourly mean ozone in micrograms per cube metre
     * @param nitrogenDioxide hourly mean nitrogen dioxide in micrograms per cube metre
     * @param sulphurDioxide 15 minute mean sulphur dioxide in micrograms per cube metre
     * @param pm25 24 hour mean PM2.5 particles in micrograms per cube metre
     * @param pm10 24 hour mean PM10 particles in micrograms per cube metre
     * @return the Daily Air Quality Index for all the parameters
     */
    public static int getDAQI(double ozone,
                              double nitrogenDioxide,
                              double sulphurDioxide,
                              double pm25,
                              double pm10) {
        // TODO: finish implementing this to return the maximum DAQI out of all
        int ozoneDAQI = DAQI.getOzoneDAQI(ozone);
        int nitrogenDioxideDAQI = DAQI.getNitrogenDioxideDAQI(nitrogenDioxide);
        int sulphuerDioxideDAQI = DAQI.getSulphurDioxideDAQI(sulphurDioxide);
        int pm25DAQI = DAQI.getPM25DAQI(pm25);
        int pm10DAQI = DAQI.getPM10DAQI(pm10);


        int[] array = {ozoneDAQI, nitrogenDioxideDAQI, sulphuerDioxideDAQI,pm25DAQI ,pm10DAQI};
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (max <= array[i]) {
                max = array[i];
            }
        }
        return max;
    }
}
