import React from 'react'
import * as Icons from './icons'
import {NavLink, Route, useLocation} from "react-router-dom";

function Icon({ icon, ...props }) {
    const Icon = Icons[icon]
    return <Icon {...props} />
}

function SidebarContent() {
    const location = useLocation();
    const routes = [
        {
            path: '/admin/dashboard', // the url
            icon: 'HomeIcon', // the component being exported from icons/index.js
            name: 'Darslar', // name that appear in Sidebar
        },
        {
            path: '/admin/group',
            icon: 'CardsIcon',
            name: 'Guruhlar',
        },
        {
            path: '/admin/room',
            icon: 'ChartsIcon',
            name: 'Xonalar',
        },
        {
            path: '/admin/subject',
            icon: 'PagesIcon',
            name: 'Fanlar',
        },
        {
            path: '/admin/teacher',
            icon: 'PeopleIcon',
            name: 'Ustozlar',
        },
    ]
    return (
        <div className="py-4 text-gray-500 text-gray-400">
            <a className="ml-6 text-lg font-bold text-white" href="#">
                Oliy Madrasa
            </a>
            <ul className="mt-6">
                {routes.map((route) =>
                    <li className="relative px-6 py-3" key={route.name}>
                        <NavLink
                            exact
                            to={route.path}
                            className="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-white"
                            activeClassName="text-gray-800 text-gray-100"
                        >
                            {location.pathname===route.path?<span
                                className="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
                                aria-hidden="true"
                            ></span>:""}

                            <Icon className="w-5 h-5" aria-hidden="true" icon={route.icon} />
                            <span className="ml-4">{route.name}</span>
                        </NavLink>
                    </li>
                )}
            </ul>
        </div>
    )
}

export default SidebarContent