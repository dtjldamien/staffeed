import React, { Fragment } from "react";

import useAuth from "@/hooks/useAuth";

const SideNavMenu = () => {
  const { logout } = useAuth();

  return (
    <Fragment>
      <button
        className="mt-auto mb-2 text-red-600 flex justify-center gap-1 hover:bg-red-100 rounded-md p-2"
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
