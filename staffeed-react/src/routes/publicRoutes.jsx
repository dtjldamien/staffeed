import React, { Suspense } from "react";
import { Navigate, Outlet } from "react-router-dom";

const Login = () => {
  return <div>Login Page</div>;
};

export const publicRoutes = [
  {
    path: "/login",
    element: <Login />,
  },
];
