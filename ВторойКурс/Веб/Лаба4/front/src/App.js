import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import {useState, useEffect} from "react";
import {Main} from "./Main";
import {Authentication} from "./Authentication";
import {Registration} from "./Registration";
import "./styles/style.css"


function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem("jwt");
        setIsAuthenticated(!!token);
    }, []);

    return (
        <Router>
            <Routes>
                <Route
                    path="/main"
                    element={
                        isAuthenticated ? <Main setAuthenticated = {setIsAuthenticated}/> : <Navigate to="/login" />
                    }
                />
                <Route
                    path="/login"
                    element={
                        isAuthenticated ? (
                            <Navigate to="/main" />
                        ) : (
                            <Authentication setIsAuthenticated={setIsAuthenticated} />
                        )
                    }
                />
                <Route
                    path="/register"
                    element={
                        isAuthenticated ? (
                            <Navigate to="/main" />
                        ) : (
                            <Registration setIsAuthenticated={setIsAuthenticated} />
                        )
                    }
                />

                <Route
                    path="/*"
                    element={
                        isAuthenticated ? (
                            <Navigate to="/main" />

                        ) : (
                            <Registration setIsAuthenticated={setIsAuthenticated} />
                        )


                    }
                    />

            </Routes>
        </Router>
    );
}

export default App;
