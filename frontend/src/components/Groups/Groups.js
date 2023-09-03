import React, {useEffect, useState} from 'react';
import PageTitle from "../Admin/PageTitle";
import {Modal} from 'react-bootstrap';

import {
    Button,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableFooter,
    TableHeader,
    TableRow
} from '@windmill/react-ui'
import apiCall from "../../Config/apiCall";
import Loading from "components/Loading/Loading";
import {ErrorNotify, SuccessNotify} from "utils/Alerts";

function Groups(props) {
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [editingID, setEditingID] = useState(false);
    const [form, setForm] = useState({
        name: "",
        count: 0
    })
    const [isSaving, setIsSaving] = useState(false);

    function getData() {
        setLoading(true)
        setTimeout(() => {
            apiCall('/groups', 'get').then(data => {
                setData(data.data)
                console.log(data.data)

                setLoading(false)
            })
        }, 1000)

    }


    useEffect(() => {
        getData()
    }, [])

    function saveData() {
        if (isSaving) return;
        setIsSaving(true);
        if (!editingID) {
            apiCall("/groups", "POST", form).then(({data}) => {
                getData()
                setIsSaving(false)
                setIsModalOpen(false);
                SuccessNotify("Group Muvaffaqiyatli Qo'shildi!")
                setEditingID(false);
            })
        } else {
            apiCall("/groups/"+editingID, "PUT", form).then(({data}) => {
                getData()
                setIsSaving(false)
                setIsModalOpen(false);
                SuccessNotify("Group Muvaffaqiyatli Taxrirlandi!")
            })
        }
        closeModal()
    }

    function closeModal() {
        setIsModalOpen(false);
        setForm({
            name: "",
            count: 0
        })
    }

    function editItem(item) {
        setForm({name:item.name,count:item.studentCount});
        setEditingID(item.id);
        setIsModalOpen(true);
    };


    function deleteItem(id) {
        setLoading(true)
        setTimeout(() => {
            apiCall("/groups/" + id, "DELETE", null).then(res => {
                SuccessNotify("Guruh Muvaffaqiyatli O'chirildi!")
                getData();
            })
        }, 1000);
    }

    return (
        <div className={` h-screen  bg-gray-900 `}>
            <PageTitle>
                Guruhlar
            </PageTitle>
            <Button className={' border-0'} onClick={() => setIsModalOpen(true)}>Yangi Guruh qo'shish</Button>

            {loading ? <Loading/> : <TableContainer className={'bg-gray-800 my-1'}>
                <Table>
                    <TableHeader className={'bg-gray-800'}>
                        <tr>

                            <TableCell>Name</TableCell>
                            <TableCell>Talabalar Soni</TableCell>
                            <TableCell>Taxrirlash</TableCell>
                        </tr>
                    </TableHeader>
                    <TableBody className={'bg-gray-800'}>
                        {data.length===0?<div className={"bg-gray-800 text-white text-center"} style={{width:"300%"}}>
                            Malumotlar Yo'q
                        </div>:data.map((user, i) => (
                            <TableRow key={i} className={'bg-gray-800 text-white'}>
                                <TableCell>
                                    <div className="flex items-center text-sm">
                                        <div>
                                            <p className="font-semibold">{user.name}</p>
                                        </div>
                                    </div>
                                </TableCell>
                                <TableCell>
                                    <div className="flex items-center text-sm">
                                        <div>
                                            <p className="font-semibold">{user.studentCount}</p>
                                        </div>
                                    </div>
                                </TableCell>
                                <TableCell>
                                    <div className="flex gap-3">
                                        <button
                                            onClick={() => deleteItem(user.id)}
                                            className="bg-red-600 hover:bg-red-800 text-white font-bold py-2 px-4 rounded-full">
                                            <i className="ri-delete-bin-line"></i>
                                        </button>
                                        <button
                                            onClick={() => editItem(user)}
                                            className="bg-blue-500 hover:bg-blue-800 text-white font-bold py-2 px-4 rounded-full">
                                            <i className="ri-pencil-line"></i>
                                        </button>
                                    </div>
                                </TableCell>
                            </TableRow>

                        ))}
                    </TableBody>
                </Table>
                <TableFooter className={'bg-gray-800'}>

                </TableFooter>
            </TableContainer>}


            <div className={'umodal'}>
                <Modal show={isModalOpen} onHide={closeModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>Yangi Guruh qo'shish</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>


                            <input
                                className={"form-control"}
                                type={'text'}
                                value={form.name}
                                placeholder={"Guruh Ismi.."}
                                onChange={(e) => setForm(({...form, name: e.target.value}))}
                            />
                            <input
                                className={"form-control mt-2"}
                                type={'number'}
                                value={form.count}
                                placeholder={"Talabalar Soni.."}
                                onChange={(e) => setForm(({...form, count: e.target.value}))}
                            />
                        <Button type={"submit"} className={'my-3 border-0 border-r-2'}
                                onClick={saveData}
                                disabled={isSaving}
                        >
                            Saqlash
                        </Button>
                    </Modal.Body>

                </Modal>
            </div>

        </div>
    );
}

export default Groups;