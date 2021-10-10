import React, { Fragment } from "react";
import { Navigate, useRoutes } from "react-router-dom";

import Auth from "@/lib/Auth";
import { protectedRoutes } from "./protectedRoutes";
import { publicRoutes } from "./publicRoutes";

const AppRoutes = () => {
  const routes = Auth.getJWT() ? protectedRoutes : publicRoutes;

  const element = useRoutes(routes);
  return <Fragment>{element}</Fragment>;
};

export default AppRoutes;
