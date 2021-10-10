import React from "react";
import PropTypes from "prop-types";

const Layout = ({ children }) => {
  return (
    <div className="h-screen w-screen flex items-stretch bg-bg-cover bg-cover">
      <div className="w-1/4 min-w-max p-10">
        <div className="bg-gray-50 min-h-full min-w-full rounded-3xl shadow-lg p-3 flex flex-col">Test</div>
      </div>
      <div className="flex-1 p-10 pl-0">
        <div className="bg-gray-50 min-h-full min-w-full rounded-3xl shadow-lg p-3">{children}</div>
      </div>
    </div>
  );
};

Layout.propTypes = {
  children: PropTypes.node,
};

export default Layout;
