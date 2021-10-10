import React from "react";
import PropTypes from "prop-types";

const Loader = ({ className, size, color }) => {
  return (
    <div className={`flex items-center justify-center space-x-2 animate-bounce ${className}`}>
      <div className={`w-${size} h-${size} ${color} rounded-full`}></div>
      <div className={`w-${size} h-${size} ${color} rounded-full`}></div>
      <div className={`w-${size} h-${size} ${color} rounded-full`}></div>
    </div>
  );
};

Loader.propTypes = {
  className: PropTypes.string,
  size: PropTypes.number,
  color: PropTypes.string,
};

Loader.defaultProps = {
  size: 4,
  color: "bg-accent-orange-3",
};

export default Loader;
