import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'react-toastify/dist/ReactToastify.css'
import './index.css';
import 'remixicon/fonts/remixicon.css'
import {BrowserRouter} from "react-router-dom";
import {SidebarProvider} from "components/Admin/Sidebar/SidebarContext";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <SidebarProvider>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </SidebarProvider>
);

