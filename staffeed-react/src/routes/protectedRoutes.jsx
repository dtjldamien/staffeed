import React, { Suspense } from "react";
import { Navigate, Outlet } from "react-router-dom";

const Dashboard = () => {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Outlet />
    </Suspense>
  );
};

const Home = () => {
  return <div>Home</div>;
};

const Test = () => {
  return <div>Test</div>;
};

export const protectedRoutes = [
  {
    path: "/dashboard",
    element: <Dashboard />,
    children: [
      { path: "test", element: <Test /> },
      { path: "", element: <Home /> },
      { path: "*", element: <Navigate to="/" /> },
    ],
  },
];
