import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import Home from "components/Home/Home";
import Login from "components/Login/Login";
import {ToastContainer} from "react-toastify";
import Dashboard from "components/Admin/Dashboard";
import Admin from "components/Admin/Admin";
import {SidebarContext} from "components/Admin/Sidebar/SidebarContext";
import Room from './components/Room/Room'
import Teacher from "./components/Teacher/Teacher";
import Subject from "./components/Subject/Subject";
import Groups from "components/Groups/Groups";
import Lesson from "components/Lesson/Lesson";
import axios from "axios";
import {domen} from "Config/apiCall";
import {useEffect} from "react";
import axiosInterceptor from "Config/axiosInterceptor";
function App() {
    const navigate = useNavigate();
    const location = useLocation();
    const permissions = [
        {url: "/admin", roles: ["ROLE_SUPER_VISOR"]},
        {url: "/admin/settings", roles: ["ROLE_SUPER_VISOR"]},
        {url: "/admin/settings/company-profile", roles: ["ROLE_SUPER_VISOR"]},
        {url: "/admin/teritory", roles: ["ROLE_SUPER_VISOR"]},
        {url: "/admin/clients", roles: ["ROLE_SUPER_VISOR"]},
        {url: "/admin/clients_on_the_map", roles: ["ROLE_SUPER_VISOR"]},
        {url: "/admin/settings/customer-category", roles: ["ROLE_SUPER_VISOR"]},
    ];

    function hasPermissions() {
        let count = 0;
        permissions.map((item, index) => {
            if (item.url === location.pathname) {
                count = count + 1;
            }
        });
        if (count === 1) {
            if (localStorage.getItem("access_token") !== null) {
                axios({
                    url: domen + "/users/me",
                    method: "GET",
                    headers: {
                        token: localStorage.getItem("access_token"),
                    },
                })
                    .then((res) => {
                        let s = false;
                        permissions.map((item) => {
                            if (item.url === location.pathname) {
                                res.data.authorities.map((i1) => {
                                    if (item.roles.includes(i1.roleName)) {
                                        s = true;
                                    }
                                });
                            }
                        });
                        if (!s) {
                            navigate("/404");
                        }
                    })
                    .catch((err) => {
                        console.log(err)
                        if (localStorage.getItem("no_token") === "sorry") {
                            navigate("/login");
                            for (let i = 0; i < 1; i++) {
                                window.location.reload();
                            }
                        }
                        if (err.response.status === 401) {
                            axiosInterceptor({
                                url:
                                    domen +
                                    "/auth/refresh?refreshToken=" +
                                    localStorage.getItem("refresh_token"),
                                method: "POST",
                            })
                                .then((res) => {
                                    localStorage.setItem("access_token", res.data);
                                    window.location.reload();
                                })
                                .catch((err) => {
                                    navigate("/login");
                                });
                        }
                    });
            } else {
                navigate("/404");
            }
        }
    }
    useEffect(()=>{
        hasPermissions();
    },[location.pathname])

    return (
        <div className="App">
            <ToastContainer/>
            <Routes>
                <Route path={"/"} element={<Home/>}/>
                <Route path={"/login"} element={<Login/>}/>
                <Route path={"/admin"} element={<Admin/>} >
                    <Route path={"/admin/room"} element={<Room />}/>
                    <Route path={"/admin/teacher"} element={<Teacher />}/>
                    <Route path={"/admin/subject"} element={<Subject />}/>
                    <Route path={"/admin/group"} element={<Groups />} />
                    <Route path={"/admin/dashboard"} element={<Lesson />} />
                </Route>
            </Routes>
        </div>
    );
}

export default App;
