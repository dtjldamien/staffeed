import axios from "axios";
import Cookies from "js-cookie";

import { BACKEND_URL } from "@/config";

const client = axios.create({
  baseURL: BACKEND_URL,
  withCredentials: true,
});

const spare = axios.create({
  baseURL: BACKEND_URL,
  withCredentials: true,
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

const getJWT = () => {
  return Cookies.get("t1");
};

const register = (newUser) => {
  return client.post("/v1/vendors", newUser);
};

const login = (user) => {
  return spare.post(`/auth`, user).then((res) => {
    // console.log(res);
    client.defaults.headers.common.Authorization = `Bearer ${res.data.access}`;
    Cookies.set("t1", res.data.access);
    return res;
  });
};

const logout = () => {
  // remove jwt from cookies
  Cookies.remove("t1");
};

const Auth = {
  client,
  login,
  logout,
  register,
  getJWT,
};

export default Auth;
