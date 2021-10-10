import React, { Suspense } from "react";
import { Navigate, Outlet } from "react-router-dom";

import Layout from "@/components/Layout";

const Dashboard = () => {
  return (
    <Layout>
      <Suspense fallback={<div>Loading...</div>}>
        <Outlet />
      </Suspense>
    </Layout>
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
