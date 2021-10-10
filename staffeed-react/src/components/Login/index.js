import React, { useState } from "react";

import useAuth from "@/hooks/useAuth";
import Loader from "@/components/Loader";

const Login = () => {
  const [cred, setCred] = useState({
    username: "",
    password: "",
  });

  const { login, loading, error } = useAuth();

  const handleChange = (e) => {
    setCred({
      ...cred,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <div className="grid min-h-screen min-w-full bg-bg-cover bg-cover" style={{ placeItems: "center" }}>
      <div className="max-w-xs">
        <form className="bg-white rounded px-8 pt-6 pb-8 mb-4 shadow-2xl">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
              Username
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              id="username"
              name="username"
              type="text"
              placeholder="Username"
              value={cred.username}
              onChange={handleChange}
            />
          </div>
          <div className="mb-6">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
              Password
            </label>
            <input
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
              id="password"
              type="password"
              name="password"
              placeholder="*********"
              value={cred.password}
              onChange={handleChange}
            />
            {/* <p className="text-red-500 text-xs italic">Please choose a password.</p> */}
          </div>
          <div className="flex gap-2 flex-col items-center">
            <button
              className="bg-accent-orange-3 hover:bg-accent-orange-2 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full h-10"
              type="submit"
              onClick={(e) => {
                e.preventDefault();
                login(cred);
              }}
              disabled={loading}
            >
              {loading ? <Loader size={2} color="bg-white" /> : "Sign In"}
            </button>
            {error && <p className="text-red-500 text-xs italic">Invalid credentials. Try again.</p>}
            {/* <a className="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="#">
              Forgot Password?
            </a> */}
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
