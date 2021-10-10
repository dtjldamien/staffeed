import React, { useState, createContext, useMemo, useContext } from "react";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

import { login as loginApi, logout as logoutApi } from "@/lib/api";

const initialContext = {
  user: {},
  loading: false,
  error: false,
  login: () => {},
  logout: () => {},
};

export const AuthContext = createContext(initialContext);

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();

  const [user, setUser] = useState({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(false);

  const login = (user) => {
    setLoading(true);
    loginApi(user)
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => setError(err))
      .finally(() => {
        setTimeout(() => setLoading(false), 1500);
        navigate("/");
      });
  };

  const logout = () => {
    logoutApi();
    navigate("/");
  };

  const memoizedContext = useMemo(
    () => ({
      user: user,
      loading: loading,
      error: error,
      login: login,
      logout: logout,
    }),
    [user, loading, error]
  );

  return <AuthContext.Provider value={memoizedContext}>{children}</AuthContext.Provider>;
};

AuthProvider.propTypes = {
  children: PropTypes.node,
};

const useAuth = () => {
  return useContext(AuthContext);
};

export default useAuth;
