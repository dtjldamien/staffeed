import axios from "axios";
import Cookies from "js-cookie";

import { BACKEND_URL } from "@/config";

export const client = axios.create({
  baseURL: BACKEND_URL,
});

export const spareClient = axios.create({
  baseURL: BACKEND_URL,
});

// set request interceptor to use access token if exists
client.interceptors.request.use(
  (config) => {
    if (Cookies.get("t1")) {
      config.headers.Authorization = `Bearer ${Cookies.get("t1")}`;
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

export const getJWT = () => {
  return Cookies.get("t1");
};

export const register = (newUser) => {
  return client.post("/v1/vendors", newUser);
};

export const login = ({ username, password }) => {
  return spareClient
    .post(`/auth/login`, {
      username: username,
      password: password,
    })
    .then((res) => {
      // console.log(res);
      client.defaults.headers.common.Authorization = `Bearer ${res.data.access}`;
      Cookies.set("t1", res.data.token);
      return res;
    })
    .then(async (res) => {
      return await client.get(`/user/${res.data.id}`);
    });
};

export const logout = () => {
  // remove jwt from cookies
  Cookies.remove("t1");
};
