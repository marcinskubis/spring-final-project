import {
  Navigate,
  RouterProvider,
  createBrowserRouter,
  Outlet,
} from "react-router-dom";
import "./App.css";
import Login from "./components/Login";
import Register from "./components/Register";
import MainPage from "./components/MainPage";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: (
        <div className="flex flex-col justify-center items-center w-full mx-auto min-h-screen bg-slate-900">
          <Outlet />
        </div>
      ),

      children: [
        {
          index: true,
          element: <Navigate to="login" />,
        },
        {
          path: "login",
          element: <Login />,
        },
        {
          path: "register",
          element: <Register />,
        },
        {
          path: "mainpage",
          element: <MainPage />,
        },
      ],
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
