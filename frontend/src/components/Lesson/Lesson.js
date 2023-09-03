import React, { useEffect, useState } from 'react';
import PageTitle from "../Admin/PageTitle";
import apiCall from "../../Config/apiCall";
import Loading from "components/Loading/Loading";
import { Modal } from "react-bootstrap";
import { Button } from "@windmill/react-ui";
import Select from 'react-select';
import {toast, ToastContainer} from "react-toastify";

function Lesson(props) {
    const [loading, setLoading] = useState(false);
    const [currentGroup, setCurrentGroup] = useState({});
    const [rooms, setRooms] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [subject, setSubject] = useState([]);
    const [groups, setGroups] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [lesson, setLesson] = useState([]);
    const [weekdays, setWeekdays] = useState([]);

    const [selectedTeacher, setSelectedTeacher] = useState("");
    const [selectedRoom, setSelectedRoom] = useState("");
    const [selectedSubject, setSelectedSubject] = useState("");
    const [selectedPara, setSelectedPara] = useState("");
    const [darsType, setDarsType] = useState(false);
    const [currentWeek, setCurrentWeek] = useState('');
    const [selectedGroups, setSelectedGroups] = useState([

    ]);


    function getData() {
        setLoading(true);
        setTimeout(() => {
            apiCall("/room", "GET", null).then(({ data }) => {
                setRooms(data);
            });
            apiCall("/teacher", "GET", null).then(({ data }) => {
                setTeachers(data);
            });
            apiCall("/subject", "GET", null).then(({ data }) => {
                setSubject(data);
            });
            apiCall("/week", "GET", null).then(({ data }) => {
                setWeekdays(data);
            });
            apiCall("/groups", "GET", null).then(({ data }) => {
                setGroups(data);
                setLoading(false);
            });
        }, 1000);
    }

    function getGroup() {
        const groupId = localStorage.getItem('selectedGroup');

        if (groupId !== undefined && groupId !== null && groupId && groupId !== '') {
            getGroupById(groupId);
            apiCall("/lesson/" + groupId, "GET", null).then(({ data }) => {
                setLesson(data);
            });
        }
    }

    useEffect(() => {
        getData();
        getGroup();
    }, []);

    function changeCurrentGroup(value) {
        getGroupById(value);
        localStorage.setItem("selectedGroup", value);
    }

    function getGroupById(groupId) {
        apiCall("/groups/" + groupId, "GET", null).then(({ data }) => {
            setCurrentGroup(data.body);
        });
    }

    function openModal(id) {
        setIsModalOpen(true);
        setSelectedTeacher("");
        setSelectedRoom("");
        setSelectedSubject("");
        setSelectedPara("");
        setCurrentWeek(id);
    }
    function closeModal() {
        setSelectedTeacher("");
        setSelectedRoom("");
        setSelectedSubject("");
        setSelectedPara("");
        setCurrentWeek('');
        setIsModalOpen(false);
    }
    function saveLesson(){
        const savedData = {
            roomId: selectedRoom,
            teacherId: selectedTeacher,
            para: selectedPara,
            subjectId: selectedSubject,
            weekId: currentWeek,
            groupIds: selectedGroups.map(group => group.value),
            lessonType:darsType
        };
        apiCall('/lesson/'+currentGroup.id,'post', savedData).then(res=>{
            toast.success(`Xona ismi bo'sh bo'lishi mumkin emas`);

            closeModal();
        }).catch(err=>{
            toast.error(err.message);

        })
    }

    return (
        <div className={` h-screen  bg-gray-900 `}>
            <PageTitle>
                Guruhlar
            </PageTitle>

            <div className="d-flex justify-content-between">
                <div className={"w-25 pb-4"}>
                    <select
                        className={"form-select"}
                        value={currentGroup.id || "DEFAULT"} // Use value instead of defaultValue
                        onChange={(e) => changeCurrentGroup(e.target.value)}
                    >
                        <option value="DEFAULT" disabled>Guruhni Tanlang!</option>
                        {currentGroup.id && (
                            <option value={currentGroup.id}>{currentGroup.name}</option>
                        )}
                        {groups
                            .filter((item) => item.id !== currentGroup.id)
                            .map((item, index) => (
                                <option key={item.id} value={item.id}>
                                    {item.name}
                                </option>
                            ))}
                    </select>
                </div>
                {currentGroup.name ? (
                    <PageTitle margin="">
                        Tanlangan Guruh: {currentGroup?.name}
                    </PageTitle>
                ) : ''}

            </div>

            {currentGroup.name ? (
                <PageTitle margin="">
                    Darslar
                </PageTitle>
            ) : ''}
            {loading ? (
                <Loading />
            ) : (
                <div>
                    {!(currentGroup.name) ? (
                        <PageTitle>
                            <i className={'my-2 '}>Guruhni tanlang!</i>
                        </PageTitle>
                    ) : (
                        <div className={'d-flex h-screen  bg-gray-900 row'}>
                            {weekdays.map(week =>
                                <div className={'col-4'} key={week.id}>
                                    <div onClick={() => openModal(week.id)} className={'card-header bg-warning h-10 text-center'}>{week.name}<b>+</b></div>
                                    <div className={'card-body'}>
                                        <div className={'border bg-amber-50 my-1 '}>
                                            <p>1-para</p>
                                            <p>Fan: nimadir</p>
                                            <p>Xona: nimadir</p>
                                            <p>O'qtuvchi: nimadir</p>
                                        </div>
                                        <div className={'border bg-amber-50 my-1 '}>
                                            <p>2-para</p>
                                            <p>Fan: nimadir</p>
                                            <p>Xona: nimadir</p>
                                            <p>O'qtuvchi: nimadir</p>
                                        </div>
                                        <div className={'border bg-amber-50 my-1 '}>
                                            <p>3-para</p>
                                            <p>Fan: nimadir</p>
                                            <p>Xona: nimadir</p>
                                            <p>O'qtuvchi: nimadir</p>
                                        </div>
                                    </div>
                                </div>
                            )}
                        </div>
                    )}

                    <div className={'umodal'}>
                        <Modal size={'xl'} show={isModalOpen} onHide={closeModal}>
                            <Modal.Header closeButton>
                                <Modal.Title>-----title</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <div className={'row d-flex justify-around'}>
                                    <div className="mb-3 col-5">
                                        <label htmlFor="subject" className="form-label">Subject</label>
                                        <select
                                            id="subject"
                                            className="form-select"
                                            value={selectedSubject}
                                            onChange={(e) => setSelectedSubject(e.target.value)}
                                        >
                                            <option value="">Select a subject</option>
                                            {subject
                                                .map((item, index) => (
                                                    <option key={item.id} value={item.id}>
                                                        {item.name}
                                                    </option>
                                                ))}
                                        </select>
                                    </div>
                                    <div className="mb-3 col-5">
                                        <label htmlFor="para" className="form-label">Para</label>
                                        <select
                                            id="para"
                                            className="form-select"
                                            value={selectedPara}
                                            onChange={(e) => setSelectedPara(e.target.value)}
                                        >
                                            <option value="">Select a para</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                        </select>
                                    </div>
                                    <div className="mb-3 col-5">
                                        <label htmlFor="room" className="form-label">Room</label>
                                        <select
                                            id="room"
                                            className="form-select"
                                            value={selectedRoom}
                                            onChange={(e) => setSelectedRoom(e.target.value)}
                                        >
                                            <option value="">Select a room</option>
                                            {rooms
                                                .map((item, index) => (
                                                    <option key={item.id} value={item.id}>
                                                        {item.name}
                                                    </option>
                                                ))}
                                        </select>
                                    </div>
                                    <div className="mb-3 col-5">
                                        <label htmlFor="teacher" className="form-label">Teacher</label>
                                        <select
                                            id="teacher"
                                            className="form-select"
                                            value={selectedTeacher}
                                            onChange={(e) => setSelectedTeacher(e.target.value)}
                                        >
                                            <option value="">Select a teacher</option>
                                            {teachers
                                                .map((item, index) => (
                                                    <option key={item.id} value={item.id}>
                                                        {item.name}
                                                    </option>
                                                ))}
                                        </select>
                                    </div>
                                    <div className="mb-3 col-5">
                                        <label htmlFor="type" className="form-label">Dars turi</label>
                                        <select
                                            id="para"
                                            className="form-select"
                                            value={darsType}
                                            onChange={(e) => setDarsType(e.target.value)}
                                        >
                                            <option value="false">Yakka dars</option>
                                            <option value="true">Maruza dars</option>
                                        </select>
                                    </div>


                                    <div className="mb-3 col-5">
                                        <label htmlFor="group" className="form-label">Guruh</label>
                                        <Select
                                            options={groups.filter(item=>item.id!==currentGroup.id).map(group => ({ value: group.id, label: group.name }))}
                                            isDisabled={!darsType}
                                            value={selectedGroups}
                                            onChange={(selectedOptions) => setSelectedGroups(selectedOptions)}
                                            isMulti={true}
                                        />
                                    </div>

                                </div>

                                <Button
                                    type="submit"
                                    className="my-3 border-0 border-r-2"
                                    onClick={() => {saveLesson()    }}
                                >
                                    Saqlash
                                </Button>
                            </Modal.Body>
                        </Modal>
                    </div>
                </div>
            )}
            <ToastContainer/>

        </div>
    );
}

export default Lesson;
