import React, { Fragment } from "react";
import { NavLink } from "react-router-dom";

import useAuth from "@/hooks/useAuth";

const SideNavMenu = () => {
  const { logout } = useAuth();
  return (
    <Fragment>
      <NavLink to="home" className="flex items-center gap-3 rounded-md p-2 text-gray-700 text-xl">
        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-" viewBox="0 0 20 20" fill="currentColor">
          <path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z" />
        </svg>
        Overview
      </NavLink>
      <NavLink to="test" className="flex items-center gap-3 rounded-md p-2 text-gray-700 text-xl">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className="h-6 w-6"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z"
          />
        </svg>
        Wellness
      </NavLink>
      <button
        className="mt-auto mb-2 text-red-600 flex justify-center gap-2 hover:bg-red-100 rounded-md p-2"
        onClick={() => logout()}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className="h-6 w-6"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"
          />
        </svg>
        Logout
      </button>
    </Fragment>
  );
};

export default SideNavMenu;
