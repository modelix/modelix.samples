<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:dfa26643-4653-44bc-9dfe-5a6581bcd381(University.Schedule.structure)">
  <persistence version="9" />
  <languages>
    <use id="c72da2b9-7cce-4447-8389-f407dc1158b7" name="jetbrains.mps.lang.structure" version="9" />
    <devkit ref="78434eb8-b0e5-444b-850d-e7c4ad2da9ab(jetbrains.mps.devkit.aspect.structure)" />
  </languages>
  <imports>
    <import index="ol3p" ref="r:14e3d2ac-611e-446b-89ae-bece9b468ddc(University.Schedule.Time.structure)" />
    <import index="tpck" ref="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="c72da2b9-7cce-4447-8389-f407dc1158b7" name="jetbrains.mps.lang.structure">
      <concept id="3348158742936976480" name="jetbrains.mps.lang.structure.structure.EnumerationMemberDeclaration" flags="ng" index="25R33">
        <property id="1421157252384165432" name="memberId" index="3tVfz5" />
      </concept>
      <concept id="3348158742936976479" name="jetbrains.mps.lang.structure.structure.EnumerationDeclaration" flags="ng" index="25R3W">
        <child id="3348158742936976577" name="members" index="25R1y" />
      </concept>
      <concept id="1082978164218" name="jetbrains.mps.lang.structure.structure.DataTypeDeclaration" flags="ng" index="AxPO6">
        <property id="7791109065626895363" name="datatypeId" index="3F6X1D" />
      </concept>
      <concept id="1082978499127" name="jetbrains.mps.lang.structure.structure.ConstrainedDataTypeDeclaration" flags="ng" index="Az7Fb">
        <property id="1083066089218" name="constraint" index="FLfZY" />
      </concept>
      <concept id="1169125787135" name="jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration" flags="ig" index="PkWjJ">
        <property id="6714410169261853888" name="conceptId" index="EcuMT" />
        <property id="4628067390765956802" name="abstract" index="R5$K7" />
        <child id="1071489727083" name="linkDeclaration" index="1TKVEi" />
        <child id="1071489727084" name="propertyDeclaration" index="1TKVEl" />
      </concept>
      <concept id="1169127622168" name="jetbrains.mps.lang.structure.structure.InterfaceConceptReference" flags="ig" index="PrWs8">
        <reference id="1169127628841" name="intfc" index="PrY4T" />
      </concept>
      <concept id="1071489090640" name="jetbrains.mps.lang.structure.structure.ConceptDeclaration" flags="ig" index="1TIwiD">
        <property id="1096454100552" name="rootable" index="19KtqR" />
        <reference id="1071489389519" name="extends" index="1TJDcQ" />
        <child id="1169129564478" name="implements" index="PzmwI" />
      </concept>
      <concept id="1071489288299" name="jetbrains.mps.lang.structure.structure.PropertyDeclaration" flags="ig" index="1TJgyi">
        <property id="241647608299431129" name="propertyId" index="IQ2nx" />
        <reference id="1082985295845" name="dataType" index="AX2Wp" />
      </concept>
      <concept id="1071489288298" name="jetbrains.mps.lang.structure.structure.LinkDeclaration" flags="ig" index="1TJgyj">
        <property id="1071599776563" name="role" index="20kJfa" />
        <property id="1071599893252" name="sourceCardinality" index="20lbJX" />
        <property id="1071599937831" name="metaClass" index="20lmBu" />
        <property id="241647608299431140" name="linkId" index="IQ2ns" />
        <reference id="1071599976176" name="target" index="20lvS9" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1133920641626" name="jetbrains.mps.lang.core.structure.BaseConcept" flags="ng" index="2VYdi">
        <property id="1193676396447" name="virtualPackage" index="3GE5qa" />
      </concept>
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="1TIwiD" id="3_cs9tOsUh0">
    <property role="EcuMT" value="4128798754188010560" />
    <property role="TrG5h" value="Lecture" />
    <property role="3GE5qa" value="education" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOt5VW" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188058364" />
      <property role="20kJfa" value="isInRoom" />
      <ref role="20lvS9" node="3_cs9tOsUhk" resolve="Room" />
    </node>
    <node concept="1TJgyj" id="3_cs9tOt5VN" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188058355" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="schedule" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="3_cs9tOsUh8" resolve="Schedule" />
    </node>
    <node concept="1TJgyj" id="4nVNu_Ykzya" role="1TKVEi">
      <property role="IQ2ns" value="5042850610501793930" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="requiredEquipment" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="2oZF3J5qwbT" resolve="Equipment" />
    </node>
    <node concept="PrWs8" id="3_cs9tOsUh1" role="PzmwI">
      <ref role="PrY4T" to="tpck:h0TrEE$" resolve="INamedConcept" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUh3" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010563" />
      <property role="TrG5h" value="description" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUh5" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010565" />
      <property role="TrG5h" value="maximumCapacity" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUh8">
    <property role="EcuMT" value="4128798754188010568" />
    <property role="TrG5h" value="Schedule" />
    <property role="R5$K7" value="true" />
    <property role="3GE5qa" value="scheduling" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="7nt_FL0QX6I" role="1TKVEi">
      <property role="IQ2ns" value="8493110207576789422" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="time" />
      <ref role="20lvS9" to="ol3p:4nVNu_YkHnY" resolve="Time" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUhk">
    <property role="EcuMT" value="4128798754188010580" />
    <property role="TrG5h" value="Room" />
    <property role="3GE5qa" value="facilities" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="2oZF3J5qwbW" role="1TKVEi">
      <property role="IQ2ns" value="2756110869689139964" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="equipment" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="2oZF3J5qwbT" resolve="Equipment" />
    </node>
    <node concept="1TJgyi" id="2oZF3J5qwHr" role="1TKVEl">
      <property role="IQ2nx" value="2756110869689142107" />
      <property role="TrG5h" value="number" />
      <ref role="AX2Wp" node="2oZF3J5qwHu" resolve="RoomNumber" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUhn" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010583" />
      <property role="TrG5h" value="maximumCapacity" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
    <node concept="PrWs8" id="3_cs9tOsUhl" role="PzmwI">
      <ref role="PrY4T" to="tpck:h0TrEE$" resolve="INamedConcept" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUhs">
    <property role="EcuMT" value="4128798754188010588" />
    <property role="TrG5h" value="RoomList" />
    <property role="19KtqR" value="true" />
    <property role="3GE5qa" value="facilities" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOsUht" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188010589" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="rooms" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="3_cs9tOsUhk" resolve="Room" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOt5DC">
    <property role="EcuMT" value="4128798754188057192" />
    <property role="TrG5h" value="LectureList" />
    <property role="19KtqR" value="true" />
    <property role="3GE5qa" value="education" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOt5DD" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188057193" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="lectures" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="3_cs9tOsUh0" resolve="Lecture" />
    </node>
  </node>
  <node concept="25R3W" id="2oZF3J5qvtq">
    <property role="3F6X1D" value="2756110869689136986" />
    <property role="TrG5h" value="EquipmentList" />
    <property role="3GE5qa" value="facilities" />
    <node concept="25R33" id="2oZF3J5qvtr" role="25R1y">
      <property role="3tVfz5" value="2756110869689136987" />
      <property role="TrG5h" value="TV" />
    </node>
    <node concept="25R33" id="2oZF3J5qvts" role="25R1y">
      <property role="3tVfz5" value="2756110869689136988" />
      <property role="TrG5h" value="Projector" />
    </node>
    <node concept="25R33" id="2oZF3J5qvu6" role="25R1y">
      <property role="3tVfz5" value="2756110869689137030" />
      <property role="TrG5h" value="Camera" />
    </node>
    <node concept="25R33" id="2oZF3J5qvtv" role="25R1y">
      <property role="3tVfz5" value="2756110869689136991" />
      <property role="TrG5h" value="Microphone" />
    </node>
    <node concept="25R33" id="2oZF3J5qvtz" role="25R1y">
      <property role="3tVfz5" value="2756110869689136995" />
      <property role="TrG5h" value="PresenterDesk" />
    </node>
    <node concept="25R33" id="2oZF3J5qvtC" role="25R1y">
      <property role="3tVfz5" value="2756110869689137000" />
      <property role="TrG5h" value="Smartboard" />
    </node>
    <node concept="25R33" id="2oZF3J5qvtI" role="25R1y">
      <property role="3tVfz5" value="2756110869689137006" />
      <property role="TrG5h" value="FlipChart" />
    </node>
    <node concept="25R33" id="2oZF3J5qvtP" role="25R1y">
      <property role="3tVfz5" value="2756110869689137013" />
      <property role="TrG5h" value="Ethernet" />
    </node>
    <node concept="25R33" id="2oZF3J5qvtX" role="25R1y">
      <property role="3tVfz5" value="2756110869689137021" />
      <property role="TrG5h" value="SecondScreen" />
    </node>
  </node>
  <node concept="1TIwiD" id="2oZF3J5qwbT">
    <property role="EcuMT" value="2756110869689139961" />
    <property role="3GE5qa" value="facilities" />
    <property role="TrG5h" value="Equipment" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyi" id="2oZF3J5qwbU" role="1TKVEl">
      <property role="IQ2nx" value="2756110869689139962" />
      <property role="TrG5h" value="equipment" />
      <ref role="AX2Wp" node="2oZF3J5qvtq" resolve="EquipmentList" />
    </node>
  </node>
  <node concept="Az7Fb" id="2oZF3J5qwHu">
    <property role="3F6X1D" value="2756110869689142110" />
    <property role="3GE5qa" value="facilities" />
    <property role="TrG5h" value="RoomNumber" />
    <property role="FLfZY" value="[0-9]+\\.[0-9]*" />
  </node>
  <node concept="1TIwiD" id="2oZF3J5qIHz">
    <property role="EcuMT" value="2756110869689199459" />
    <property role="3GE5qa" value="people" />
    <property role="TrG5h" value="Tutor" />
    <ref role="1TJDcQ" node="2oZF3J5qIH$" resolve="Person" />
    <node concept="1TJgyj" id="7nt_FL0R3An" role="1TKVEi">
      <property role="IQ2ns" value="8493110207576816023" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="offersLecture" />
      <property role="20lbJX" value="fLJekj6/_1__n" />
      <ref role="20lvS9" node="7nt_FL0QXRj" resolve="LectureReference" />
    </node>
  </node>
  <node concept="1TIwiD" id="2oZF3J5qIH$">
    <property role="EcuMT" value="2756110869689199460" />
    <property role="3GE5qa" value="people" />
    <property role="TrG5h" value="Person" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="1rwgWV86q64" role="1TKVEi">
      <property role="IQ2ns" value="1648392019017048452" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="dateOfBirth" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" to="ol3p:4nVNu_YkHnZ" resolve="Date" />
    </node>
    <node concept="PrWs8" id="2oZF3J5qIH_" role="PzmwI">
      <ref role="PrY4T" to="tpck:h0TrEE$" resolve="INamedConcept" />
    </node>
  </node>
  <node concept="1TIwiD" id="7nt_FL0QVER">
    <property role="EcuMT" value="8493110207576783543" />
    <property role="3GE5qa" value="scheduling" />
    <property role="TrG5h" value="WeeklyRecurringSchedule" />
    <ref role="1TJDcQ" node="3_cs9tOsUh8" resolve="Schedule" />
    <node concept="1TJgyj" id="7nt_FL0QX6O" role="1TKVEi">
      <property role="IQ2ns" value="8493110207576789428" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="startDate" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" to="ol3p:4nVNu_YkHnZ" resolve="Date" />
    </node>
    <node concept="1TJgyj" id="7nt_FL0QX6Q" role="1TKVEi">
      <property role="IQ2ns" value="8493110207576789430" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="endDate" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" to="ol3p:4nVNu_YkHnZ" resolve="Date" />
    </node>
  </node>
  <node concept="1TIwiD" id="7nt_FL0QWo5">
    <property role="EcuMT" value="8493110207576786437" />
    <property role="3GE5qa" value="scheduling" />
    <property role="TrG5h" value="OnetimeSchedule" />
    <ref role="1TJDcQ" node="3_cs9tOsUh8" resolve="Schedule" />
    <node concept="1TJgyj" id="7nt_FL0QX6M" role="1TKVEi">
      <property role="IQ2ns" value="8493110207576789426" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="date" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" to="ol3p:4nVNu_YkHnZ" resolve="Date" />
    </node>
  </node>
  <node concept="1TIwiD" id="7nt_FL0QXRd">
    <property role="EcuMT" value="8493110207576792525" />
    <property role="3GE5qa" value="scheduling" />
    <property role="TrG5h" value="SemesterPlan" />
    <property role="19KtqR" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="4nVNu_Yl6ri" role="1TKVEi">
      <property role="IQ2ns" value="5042850610501936850" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="schedule" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="3_cs9tOsUh8" resolve="Schedule" />
    </node>
  </node>
  <node concept="1TIwiD" id="7nt_FL0QXRj">
    <property role="EcuMT" value="8493110207576792531" />
    <property role="TrG5h" value="LectureReference" />
    <property role="3GE5qa" value="education" />
    <node concept="1TJgyj" id="7nt_FL0QXRk" role="1TKVEi">
      <property role="20lbJX" value="fLJekj4/1" />
      <property role="IQ2ns" value="8493110207576792532" />
      <property role="20kJfa" value="lecture" />
      <ref role="20lvS9" node="3_cs9tOsUh0" resolve="Lecture" />
    </node>
  </node>
  <node concept="1TIwiD" id="7nt_FL0R4pW">
    <property role="EcuMT" value="8493110207576819324" />
    <property role="3GE5qa" value="people" />
    <property role="TrG5h" value="TutorList" />
    <property role="19KtqR" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="7nt_FL0R4pX" role="1TKVEi">
      <property role="IQ2ns" value="8493110207576819325" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="tutors" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="2oZF3J5qIHz" resolve="Tutor" />
    </node>
  </node>
</model>

