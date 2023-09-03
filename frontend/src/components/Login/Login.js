import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {domen} from "Config/apiCall";
import {ErrorNotify, SuccessNotify} from "utils/Alerts";
import axiosInterceptor from "Config/axiosInterceptor";
import MyImage from './image.jpeg'
import {Button, Label} from '@windmill/react-ui'

function Login(props) {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        username: "",
        password: ''
    });

    function hasPermissionRoleSuperVisor() {
        if (localStorage.getItem("access_token") !== null) {
            try {
                axiosInterceptor({
                    url: domen + "/users/me", method: "GET",
                    headers: {
                        token: localStorage.getItem("access_token"),
                    },
                }).then(res => {
                    navigate("/admin")
                }).catch(e=>{

                })
            } catch (err) {
                if (err.response.status === 401) {
                    if (localStorage.getItem("refresh_token") !== null) {
                        try {
                            axiosInterceptor({
                                url: domen + "/auth/refresh?refreshToken=" + localStorage.getItem("refresh_token"),
                                method: "POST"
                            }).then(res => {
                                navigate("/admin")
                            })
                        } catch (err2) {
                            localStorage.clear()
                        }
                    }
                } else {
                    localStorage.clear()
                }
            }
        }

    }

    useEffect(() => {
        hasPermissionRoleSuperVisor();
    }, [])


    function login(e) {
        e.preventDefault();
        axiosInterceptor({url: domen + "/auth/login", method: "POST", data: form}).then(res => {
            localStorage.setItem("access_token", res.data.access_token);
            localStorage.setItem("refresh_token", res.data.refresh_token);
            localStorage.setItem("no_token", "success");
            SuccessNotify("Logined Successfully!");
            navigate("/admin");
        }).catch(err => {
            ErrorNotify("Password Or Username Is Wrong!");
            localStorage.clear();
        })
    }

    return (
        <div>

            <div className="flex items-center min-h-screen p-6 bg-gray-50 bg-gray-900">
                <div
                    className="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl bg-gray-800">
                    <div className="flex flex-col overflow-y-auto md:flex-row">
                        <div className="h-32 md:h-auto md:w-1/2">
                            <img
                                aria-hidden="true"
                                className="object-cover w-full h-full dark:hidden"
                                src={MyImage}
                                alt="Office"
                            />

                        </div>
                        <main className="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
                            <form onSubmit={login}>
                                <div className="w-full">
                                    <h1 className="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200">Login</h1>
                                    <Label>
                                        <input type="text" className={" mt-1 form-control"} required={true}
                                               placeholder={"username"}
                                               onChange={(e) => setForm({...form, username: e.target.value})}/>
                                    </Label>

                                    <Label className="mt-4">
                                        <input type="password" className={"mt-1 form-control"} required={true}
                                               placeholder={"password"}
                                               onChange={(e) => setForm({...form, password: e.target.value})}/>
                                    </Label>

                                    <Button className="mt-4" block onClick={login}>
                                        Log in
                                    </Button>

                                    <hr className="my-8"/>

                                </div>
                            </form>
                        </main>
                    </div>
                </div>
            </div>
            fr

        </div>
    );
}

export default Login;