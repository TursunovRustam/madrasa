import React, { useEffect, useState } from 'react';
import PageTitle from "../Admin/PageTitle";
import { Modal } from 'react-bootstrap';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableFooter,
    TableHeader,
    TableRow,
    Button
} from '@windmill/react-ui';
import apiCall from "../../Config/apiCall";
import { toast } from "react-toastify";
import Select from 'react-select';
import Loading from "../Loading/Loading"; // Import React Select

function Subject(props) {
    const [data, setData] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [subjectName, setSubjectName] = useState('');
    const [isSaving, setIsSaving] = useState(false);
    const [teachers, setTeachers] = useState([]);
    const [selectedTeachers, setSelectedTeachers] = useState([]); // State for selected teachers
    const [editingID, setEditingID] = useState(null);
    const [loading, setLoading] = useState(false);

    function getData() {
        setLoading(true)
        setTimeout(() => {
            apiCall('/subject', 'get').then(data => {
                setData(data.data)
                setLoading(false)
            })
        }, 1000)
    }

    useEffect(() => {
        apiCall('/teacher', 'get')
            .then(res => {
                setTeachers(res.data);
            })
            .catch(error => {
                console.error("Error fetching teachers:", error);
            });
        getData();
    }, []);

    function saveData() {
        if (subjectName.trim() === '' || isSaving || selectedTeachers.length === 0) {
            toast.error(` Fan nomi, o'qituvchilar yoki qo'shish muvaffaqiyatli bo'lmadi`);
            return;
        }

        const teacherIds = selectedTeachers.map(teacher => teacher.value);

        setIsSaving(true);
        console.log(teacherIds)
        const newData = { name: subjectName, teacherIds: teacherIds };

        if (!editingID) {
            apiCall('/subject', 'post', newData)
                .then((res) => {
                    toast.success(` Fan muvaffaqiyatli qo'shildi`);
                    closeModal();
                    getData();
                })
                .catch((err) => {
                    toast.error(` Fan qo'shishda xatolik`);
                })
                .finally(() => {
                    setIsSaving(false);
                });
        } else {
            apiCall('/subject/' + editingID, 'put', newData)
                .then((res) => {
                    toast.success(` Fan muvaffaqiyatli taxrirlandi`);
                    closeModal();
                    getData();
                })
                .catch((err) => {
                    toast.error(` Fan taxrirlashda xatolik`);
                })
                .finally(() => {
                    setIsSaving(false);
                });
            setEditingID(null);
        }
    }

    function editItem(item) {
        setSubjectName(item.name);
        const selectedTeachersArray = item?.teachers?.map(teacherId => {
            return { value: teacherId.id, label: teacherId.name };
        });

        setSelectedTeachers(selectedTeachersArray);
        setEditingID(item.id);
        setIsModalOpen(true);
    }



    function deleteItem(id) {
        setLoading(true);
        apiCall("/subject/" + id, "DELETE", null)
            .then(res => {
                toast.success("Fan muvaffaqiyatli o'chirildi!");
                getData();
            })
            .catch(error => {
                toast.error("Fan o'chirishda xatolik");
            })
            .finally(() => {
                setLoading(false);
            });
    }

    function closeModal() {
        setIsModalOpen(false);
        setSubjectName('');
        setSelectedTeachers([]);
        setEditingID(null);
    }

    const customStyles = {
        // Define custom styles for the Select component if needed
    };
    return (
        <div className={` h-screen  bg-gray-900 `}>
            <PageTitle>
                Fanlar
            </PageTitle>
            <Button className={' border-0'} onClick={()=>setIsModalOpen(true)}>
                Yangi Fan qo'shish
            </Button>

            {loading?<Loading/>:      <TableContainer className={'bg-gray-800 my-1'}>
                <Table>
                    <TableHeader className={'bg-gray-800'}>
                        <tr>
                            <TableCell>Fan nomi</TableCell>
                            <TableCell>O'qituvchilar</TableCell>
                            <TableCell>sana</TableCell>
                            <TableCell></TableCell>
                        </tr>
                    </TableHeader>
                    <TableBody className={'bg-gray-800'}>
                        {data.length===0?<div className={"bg-gray-800 text-white text-center"} style={{width:"300%"}}>
                            Malumotlar Yo'q
                        </div>  :data?.map((subject, i) => (
                            <TableRow key={i} className={'bg-gray-800 text-white'}>
                                <TableCell>
                                    <div className="flex items-center text-sm">
                                        <div>
                                            <p className="font-semibold">{subject.name}</p>
                                        </div>
                                    </div>
                                </TableCell>
                                <TableCell>
                                    {subject.teachers.map((teacher, index) => (
                                            <span key={index}>{teacher.name} <br/></span>
                                        ))
                                    }
                                </TableCell>

                                <TableCell>
                                    <span className="text-sm">
                                        {new Date(subject.time).toLocaleDateString()}
                                    </span>
                                </TableCell>
                                <TableCell>
                                    <div className="flex gap-3">
                                        <button
                                            onClick={() => deleteItem(subject.id)}
                                            className="bg-red-600 hover:bg-red-800 text-white font-bold py-2 px-4 rounded-full">
                                            Delete
                                        </button>
                                        <button
                                            onClick={() => editItem(subject)}
                                            className="bg-blue-500 hover:bg-blue-800 text-white font-bold py-2 px-4 rounded-full">
                                            Edit
                                        </button>
                                    </div>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                <TableFooter className={'bg-gray-800'}></TableFooter>
            </TableContainer>}

            <div className={'umodal'}>
                <Modal show={isModalOpen} onHide={closeModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>
                            {editingID ? "Fan ma'lumotlarini taxrirlash" : "Yangi Fan qo'shish"}
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <input
                            className={"form-control"}
                            type={'text'}
                            value={subjectName}
                            placeholder={'fan nomini kiriting'}
                            onChange={(e) => setSubjectName(e.target.value)}
                        />
                        <Select
                            className={'my-3'}
                            options={teachers?.map(teacher => ({ value: teacher.id, label: teacher.name }))}
                            value={selectedTeachers}
                            onChange={setSelectedTeachers}
                            styles={customStyles}
                            isMulti
                            placeholder="O'qituvchi ni tanlang"
                        />
                        <Button className={'my-3 border-0 border-r-2'}
                                onClick={saveData}
                                disabled={isSaving}
                        >
                            {isSaving ? 'Saqlanmoqda...' : 'Saqlash'}
                        </Button>
                    </Modal.Body>
                </Modal>
            </div>
        </div>
    );
}

export default Subject;
