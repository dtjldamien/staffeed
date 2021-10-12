import React from "react";
import { Navigate } from "react-router-dom";

import Login from "@/components/Login";

export const publicRoutes = [
  {
    path: "/login",
    element: <Login />,
  },
  { path: "*", element: <Navigate to="/login" /> },
];
