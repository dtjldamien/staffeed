import React, { Fragment } from "react";
import { Navigate, useRoutes } from "react-router-dom";

import { getJWT } from "@/lib/api";
import { protectedRoutes } from "./protectedRoutes";
import { publicRoutes } from "./publicRoutes";

const AppRoutes = () => {
  const routes = getJWT() ? protectedRoutes : publicRoutes;

  const element = useRoutes(routes);
  return <Fragment>{element}</Fragment>;
};

export default AppRoutes;
