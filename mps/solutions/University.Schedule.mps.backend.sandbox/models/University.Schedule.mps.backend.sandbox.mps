<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(University.Schedule.mps.backend.sandbox)">
  <persistence version="9" />
  <languages>
    <devkit ref="3f0b14cf-38db-4a9e-ae9e-6c078c16c2da(University.Schedule.Devkit)" />
  </languages>
  <imports />
  <registry>
    <language id="cafa79c7-9e66-4df7-8f06-84e4e6a7c5e6" name="University.Schedule.Time">
      <concept id="5042850610501834239" name="University.Schedule.Time.structure.Date" flags="ng" index="VEhwm">
        <property id="5042850610501834242" name="date" index="VEhJF" />
      </concept>
      <concept id="5042850610501834238" name="University.Schedule.Time.structure.Time" flags="ng" index="VEhwn">
        <property id="5042850610501834244" name="time" index="VEhJH" />
      </concept>
    </language>
    <language id="96533389-8d4c-46f2-b150-8d89155f7fca" name="University.Schedule">
      <concept id="8493110207576819324" name="University.Schedule.structure.TutorList" flags="ng" index="P0t30">
        <child id="8493110207576819325" name="tutors" index="P0t31" />
      </concept>
      <concept id="8493110207576783543" name="University.Schedule.structure.WeeklyRecurringSchedule" flags="ng" index="P1yKb">
        <child id="8493110207576789428" name="startDate" index="P1$s8" />
        <child id="8493110207576789430" name="endDate" index="P1$sa" />
      </concept>
      <concept id="8493110207576792531" name="University.Schedule.structure.LectureReference" flags="ng" index="P1$HJ">
        <reference id="8493110207576792532" name="lecture" index="P1$HC" />
      </concept>
      <concept id="8493110207576786437" name="University.Schedule.structure.OnetimeSchedule" flags="ng" index="P1_2T">
        <child id="8493110207576789426" name="date" index="P1$se" />
      </concept>
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
        <child id="5042850610501793930" name="requiredEquipment" index="VEvlz" />
        <child id="4128798754188058355" name="schedule" index="1de2T$" />
      </concept>
      <concept id="4128798754188010568" name="University.Schedule.structure.Schedule" flags="ng" index="1dfXjv">
        <child id="8493110207576789422" name="time" index="P1$si" />
      </concept>
      <concept id="2756110869689199459" name="University.Schedule.structure.Tutor" flags="ng" index="3zlkto">
        <child id="8493110207576816023" name="offersLecture" index="P0qWF" />
      </concept>
      <concept id="2756110869689199460" name="University.Schedule.structure.Person" flags="ng" index="3zlktv">
        <child id="1648392019017048452" name="dateOfBirth" index="3uYu9a" />
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
    <node concept="1dfXj3" id="4nVNu_Yld8s" role="1dfXja">
      <property role="TrG5h" value="Marie" />
      <property role="3zlqtw" value="1.311" />
      <property role="1dfXj0" value="30" />
      <node concept="3zlqV2" id="7nt_FL0QUC7" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvts/Projector" />
      </node>
      <node concept="3zlqV2" id="7nt_FL0QUC9" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvtv/Microphone" />
      </node>
      <node concept="3zlqV2" id="7nt_FL0QUCc" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvu6/Camera" />
      </node>
    </node>
    <node concept="1dfXj3" id="7nt_FL0QzMr" role="1dfXja">
      <property role="TrG5h" value="Einstein" />
      <property role="3zlqtw" value="1.312" />
      <property role="1dfXj0" value="21" />
      <node concept="3zlqV2" id="7nt_FL0QUzU" role="3zlqV7">
        <property role="3zlqV1" value="2oZF3J5qvts/Projector" />
      </node>
    </node>
    <node concept="1dfXj3" id="7nt_FL0QUC2" role="1dfXja">
      <property role="TrG5h" value="Rosalind" />
      <property role="3zlqtw" value="1.333" />
      <property role="1dfXj0" value="10" />
    </node>
    <node concept="1dfXj3" id="4nVNu_Yld8K" role="1dfXja">
      <property role="TrG5h" value="Schrödinger" />
      <property role="3zlqtw" value="1.331" />
      <property role="1dfXj0" value="41" />
    </node>
  </node>
  <node concept="1de2FZ" id="3_cs9tOt5VK">
    <node concept="1dfXjn" id="7nt_FL0QUCm" role="1de2FY">
      <property role="TrG5h" value="Physics 101" />
      <property role="1dfXjk" value="You learn some basic Physics here." />
      <property role="1dfXji" value="32" />
      <ref role="1de2TF" node="7nt_FL0QzMr" resolve="Einstein" />
      <node concept="P1yKb" id="7nt_FL0QXQm" role="1de2T$">
        <node concept="VEhwn" id="4nVNu_YkUre" role="P1$si">
          <property role="VEhJH" value="08:00" />
        </node>
        <node concept="VEhwm" id="4nVNu_YkUrg" role="P1$sa">
          <property role="VEhJF" value="11.03.2024" />
        </node>
        <node concept="VEhwm" id="4nVNu_YkUri" role="P1$s8">
          <property role="VEhJF" value="10.09.2023" />
        </node>
      </node>
      <node concept="3zlqV2" id="4nVNu_Yk$oU" role="VEvlz">
        <property role="3zlqV1" value="2oZF3J5qvu6/Camera" />
      </node>
      <node concept="3zlqV2" id="4nVNu_Yk$oS" role="VEvlz">
        <property role="3zlqV1" value="2oZF3J5qvtv/Microphone" />
      </node>
    </node>
    <node concept="1dfXjn" id="7nt_FL0QVDR" role="1de2FY">
      <property role="TrG5h" value="New Stutent Welcoming" />
      <property role="1dfXjk" value="The initial welcoming of all new students" />
      <property role="1dfXji" value="99" />
      <ref role="1de2TF" node="7nt_FL0QUC2" resolve="Schrödinger" />
      <node concept="P1_2T" id="7nt_FL0QXQK" role="1de2T$">
        <node concept="VEhwm" id="4nVNu_YkUra" role="P1$se">
          <property role="VEhJF" value="10.09.2023" />
        </node>
        <node concept="VEhwn" id="4nVNu_YkUrc" role="P1$si">
          <property role="VEhJH" value="10:00" />
        </node>
      </node>
      <node concept="3zlqV2" id="4nVNu_Yld8h" role="VEvlz">
        <property role="3zlqV1" value="2oZF3J5qvtv/Microphone" />
      </node>
      <node concept="3zlqV2" id="4nVNu_Yld8j" role="VEvlz">
        <property role="3zlqV1" value="2oZF3J5qvts/Projector" />
      </node>
    </node>
  </node>
  <node concept="P0t30" id="7nt_FL0R5g_">
    <node concept="3zlkto" id="4nVNu_Yl8je" role="P0t31">
      <property role="TrG5h" value="Ripley" />
      <node concept="VEhwm" id="4nVNu_Yl8jg" role="3uYu9a">
        <property role="VEhJF" value="1986" />
      </node>
      <node concept="P1$HJ" id="7nt_FL0R4pN" role="P0qWF">
        <ref role="P1$HC" node="7nt_FL0QUCm" resolve="Physics 101" />
      </node>
      <node concept="P1$HJ" id="4nVNu_Ykzy7" role="P0qWF">
        <ref role="P1$HC" node="7nt_FL0QVDR" resolve="New Stutent Welcoming" />
      </node>
    </node>
    <node concept="3zlkto" id="4nVNu_Yl8jB" role="P0t31">
      <property role="TrG5h" value="Avery" />
      <node concept="VEhwm" id="4nVNu_Yl8jD" role="3uYu9a">
        <property role="VEhJF" value="1981" />
      </node>
      <node concept="P1$HJ" id="7nt_FL0R4pP" role="P0qWF">
        <ref role="P1$HC" node="7nt_FL0QVDR" resolve="New Stutent Welcoming" />
      </node>
    </node>
  </node>
</model>

