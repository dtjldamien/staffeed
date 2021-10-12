import React from "react";
import { BrowserRouter } from "react-router-dom";

import AppRoutes from "@/routes";
import { AuthProvider } from "@/hooks/useAuth";

const App = () => {
  return (
    <BrowserRouter>
      <AuthProvider>
        <AppRoutes />
      </AuthProvider>
    </BrowserRouter>
  );
};

export default App;
