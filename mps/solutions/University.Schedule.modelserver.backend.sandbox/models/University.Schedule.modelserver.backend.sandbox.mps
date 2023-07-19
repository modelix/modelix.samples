<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(University.Schedule.modelserver.backend.sandbox)">
  <persistence version="9" />
  <languages>
    <use id="96533389-8d4c-46f2-b150-8d89155f7fca" name="University.Schedule" version="0" />
  </languages>
  <imports />
  <registry>
    <language id="96533389-8d4c-46f2-b150-8d89155f7fca" name="University.Schedule">
      <concept id="4128798754188059567" name="University.Schedule.structure.OneOff" flags="ng" index="1de1cS" />
      <concept id="4128798754188057192" name="University.Schedule.structure.LectureList" flags="ng" index="1de2FZ">
        <child id="4128798754188057193" name="lectures" index="1de2FY" />
      </concept>
      <concept id="4128798754188010580" name="University.Schedule.structure.Room" flags="ng" index="1dfXj3">
        <property id="4128798754188010583" name="maximumCapacity" index="1dfXj0" />
        <property id="2756110869689142107" name="number" index="3zlqtw" />
        <child id="2756110869689139964" name="equipment" index="3zlqV7" />
      </concept>
      <concept id="4128798754188010588" name="University.Schedule.structure.RoomList" flags="ng" index="1dfXjb">
        <child id="4128798754188010589" name="rooms" index="1dfXja" />
      </concept>
      <concept id="4128798754188010560" name="University.Schedule.structure.Lecture" flags="ng" index="1dfXjn">
        <property id="4128798754188010565" name="maximumCapacity" index="1dfXji" />
        <property id="4128798754188010563" name="description" index="1dfXjk" />
        <reference id="4128798754188058364" name="isInRoom" index="1de2TF" />
        <child id="4128798754188058355" name="schedule" index="1de2T$" />
      </concept>
      <concept id="4128798754188010570" name="University.Schedule.structure.DateAndTime" flags="ng" index="1dfXjt">
        <property id="4128798754188010573" name="time" index="1dfXjq" />
        <property id="4128798754188010571" name="date" index="1dfXjs" />
      </concept>
      <concept id="4128798754188010569" name="University.Schedule.structure.Recurring" flags="ng" index="1dfXju" />
      <concept id="4128798754188010568" name="University.Schedule.structure.Schedule" flags="ng" index="1dfXjv">
        <child id="4128798754188010578" name="at" index="1dfXj5" />
      </concept>
      <concept id="1648392019017048449" name="University.Schedule.structure.Student" flags="ng" index="3uYu9f">
        <property id="1648392019017048454" name="semester" index="3uYu98" />
      </concept>
      <concept id="2756110869689212148" name="University.Schedule.structure.Date" flags="ng" index="3zlb3f">
        <property id="2756110869689212149" name="date" index="3zlb3e" />
      </concept>
      <concept id="2756110869689199459" name="University.Schedule.structure.Lecturer" flags="ng" index="3zlkto" />
      <concept id="2756110869689199460" name="University.Schedule.structure.Person" flags="ng" index="3zlktv">
        <property id="2756110869689199465" name="faculty" index="3zlkti" />
        <child id="1648392019017048452" name="dateOfBirth" index="3uYu9a" />
      </concept>
      <concept id="2756110869689201930" name="University.Schedule.structure.PersonList" flags="ng" index="3zll$L">
        <child id="2756110869689201931" name="lecturers" index="3zll$K" />
        <child id="2756110869689201933" name="students" index="3zll$Q" />
      </concept>
      <concept id="2756110869689139961" name="University.Schedule.structure.Equipment" flags="ng" index="3zlqV2">
        <property id="2756110869689139962" name="equipment" index="3zlqV1" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="1dfXjb" id="3_cs9tOt5VE">
    <node concept="1dfXj3" id="2oZF3J5qxem" role="1dfXja">
      <property role="TrG5h" value="Einstein" />
      <property role="1dfXj0" value="31" />
      <property role="3zlqtw" value="3.131" />
      <node concept="3zlqV2" id="2oZF3J5qwGJ" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvts/Projector" />
      </node>
      <node concept="3zlqV2" id="2oZF3J5qwGE" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvu6/Camera" />
      </node>
      <node concept="3zlqV2" id="2oZF3J5qwGG" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvtv/Microphone" />
      </node>
    </node>
    <node concept="1dfXj3" id="2oZF3J5qMx2" role="1dfXja">
      <property role="TrG5h" value="Schrödinger" />
      <property role="1dfXj0" value="21" />
      <property role="3zlqtw" value="3.132" />
      <node concept="3zlqV2" id="2oZF3J5qMx3" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvts/Projector" />
      </node>
      <node concept="3zlqV2" id="2oZF3J5qMx5" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvtv/Microphone" />
      </node>
    </node>
  </node>
  <node concept="1de2FZ" id="3_cs9tOt5VK">
    <node concept="1dfXjn" id="3_cs9tOt5VL" role="1de2FY">
      <property role="TrG5h" value="Physics 101" />
      <property role="1dfXjk" value="You learn about stuff" />
      <property role="1dfXji" value="42" />
      <ref role="1de2TF" node="3_cs9tOt5VF" resolve="EinsteinElseMore" />
      <node concept="1dfXju" id="3_cs9tOt6eE" role="1de2T$">
        <node concept="1dfXjt" id="3_cs9tOt6eF" role="1dfXj5">
          <property role="1dfXjs" value="08.03.2022" />
          <property role="1dfXjq" value="11:30:00" />
        </node>
      </node>
    </node>
    <node concept="1dfXjn" id="3_cs9tOt6yQ" role="1de2FY">
      <property role="TrG5h" value="New Students Welcome" />
      <property role="1dfXjk" value="Hello everyone" />
      <property role="1dfXji" value="69" />
      <ref role="1de2TF" node="2oZF3J5qMx2" resolve="Schrödinger" />
      <node concept="1de1cS" id="3_cs9tOt6yZ" role="1de2T$">
        <node concept="1dfXjt" id="3_cs9tOt6z2" role="1dfXj5">
          <property role="1dfXjs" value="08.03.2022" />
          <property role="1dfXjq" value="08:00:00" />
        </node>
      </node>
    </node>
  </node>
  <node concept="3zll$L" id="2oZF3J5qLNF">
    <node concept="3uYu9f" id="2oZF3J5qLNK" role="3zll$Q">
      <property role="TrG5h" value="Ripley" />
      <property role="3uYu98" value="3" />
      <property role="3zlkti" value="2oZF3J5qIHK/ArtsAndHumanities" />
      <node concept="3zlb3f" id="2oZF3J5qMwG" role="3uYu9a">
        <property role="3zlb3e" value="2000" />
      </node>
    </node>
    <node concept="3uYu9f" id="2oZF3J5qMwI" role="3zll$Q">
      <property role="TrG5h" value="Morgan" />
      <property role="3uYu98" value="6" />
      <property role="3zlkti" value="2oZF3J5qIHJ/ComputerScience" />
      <node concept="3zlb3f" id="2oZF3J5qMwJ" role="3uYu9a">
        <property role="3zlb3e" value="1997" />
      </node>
    </node>
    <node concept="3uYu9f" id="2oZF3J5qMwO" role="3zll$Q">
      <property role="TrG5h" value="Avery" />
      <property role="3uYu98" value="1" />
      <property role="3zlkti" value="2oZF3J5qIHW/Psychology" />
      <node concept="3zlb3f" id="2oZF3J5qMwP" role="3uYu9a">
        <property role="3zlb3e" value="1990" />
      </node>
    </node>
    <node concept="3zlkto" id="2oZF3J5qLNG" role="3zll$K">
      <property role="TrG5h" value="Casey" />
      <property role="3zlkti" value="2oZF3J5qIHJ/ComputerScience" />
      <node concept="3zlb3f" id="2oZF3J5qMv_" role="3uYu9a">
        <property role="3zlb3e" value="1986" />
      </node>
    </node>
    <node concept="3zlkto" id="2oZF3J5qMwW" role="3zll$K">
      <property role="TrG5h" value="Inga" />
      <property role="3zlkti" value="2oZF3J5qIHR/EnvironmentalSciences" />
      <node concept="3zlb3f" id="2oZF3J5qMwX" role="3uYu9a">
        <property role="3zlb3e" value="1983" />
      </node>
    </node>
  </node>
</model>

