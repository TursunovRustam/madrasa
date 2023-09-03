import axiosInterceptor from "./axiosInterceptor";
import {ErrorNotify} from "utils/Alerts";

export const domen = "http://localhost:8080/api";

export default function apiCall(url, method, data, ) {
    let item = localStorage.getItem("access_token");
    return axiosInterceptor({
        baseURL: domen,
        url,
        method: method,
        data,
        headers: {
            "token": item,
        },
    }).catch((err) => {
        if (err.response && err.response.status === 401) {
            return axiosInterceptor({
                url: domen+"/auth/refresh?refreshToken=" + localStorage.getItem("refresh_token"),
                method: "POST"
            }).then((res) => {
                localStorage.setItem("access_token", res.data);
                // Retry the original request with the new access token
                return axiosInterceptor({
                    baseURL: domen,
                    url,
                    method: method,
                    data,
                    headers: {
                        "token": localStorage.getItem("access_token"),
                    },
                });
            }).catch((refreshErr) => {
                window.location = "/login";
                // throw refreshErr;
            });
        } else if (err.response && (err.response.status === 404 || err.response.status === 500)) {
            ErrorNotify(err.response.data);
        }
    });
}
