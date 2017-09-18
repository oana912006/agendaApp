package com.feed.services;

import com.feed.utils.FeedConstants;

/**
 * UrlBuilder is a class used for building url by a given list of parameters and a specific method
 */
public class UrlBuilder {

    public enum ServiceMethod {
        GetAllUsers("https://randomuser.me/api/?page=0&results=100&seed=abc");

        private String methodEndPoint;
        private String[] paramNames;

        private ServiceMethod(String methodEndPoint, String... paramNames) {
            this.methodEndPoint = methodEndPoint;
            this.paramNames = paramNames;
        }

        /**
         * Get complete url based on partial url and it's parameters
         *
         * @param urlOfResource
         * @param paramValues   - parameters of url
         * @return
         */
        public String getUrl(String urlOfResource, Object... paramValues) {
            String paramsString = "?";

            for (int i = 0; i < paramValues.length; i++) {
                if (paramValues[i] != null) {
                    paramValues[i] = ((String) paramValues[i]).replace(" ", "%20");
                    paramsString = paramsString + paramNames[i].concat("=").concat(paramValues[i].toString());
                    if (i != paramValues.length - 1) {
                        paramsString = paramsString.concat("&");
                    }
                }
            }

            return urlOfResource;
        }
    }

}

