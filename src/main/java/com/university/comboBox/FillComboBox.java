package com.university.comboBox;

import com.university.db.control.*;
import com.university.db.entity.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Created by maksymmikitiuk on 4/8/17.
 */
public class FillComboBox {
    private ComboBox comboBox;

    public FillComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public void fillGroup() {
        comboBox.setItems(FXCollections.observableArrayList(new GroupController().getAllGroup()));
        comboBox.setCellFactory(new Callback<ListView<GroupsEntity>, ListCell<GroupsEntity>>() {

            @Override
            public ListCell<GroupsEntity> call(ListView<GroupsEntity> param) {
                return new ListCell<GroupsEntity>() {
                    @Override
                    public void updateItem(GroupsEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getAbbreviation());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillChair() {
        comboBox.setItems(FXCollections.observableArrayList(new ChairController().getAllChair()));
        comboBox.setCellFactory(new Callback<ListView<ChairsEntity>, ListCell<ChairsEntity>>() {

            @Override
            public ListCell<ChairsEntity> call(ListView<ChairsEntity> param) {
                return new ListCell<ChairsEntity>() {
                    @Override
                    public void updateItem(ChairsEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getAbbreviation());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillGroupForm() {
        comboBox.setItems(FXCollections.observableArrayList(new GroupFormController().getAllGroupForm()));
        comboBox.setCellFactory(new Callback<ListView<GroupformEntity>, ListCell<GroupformEntity>>() {

            @Override
            public ListCell<GroupformEntity> call(ListView<GroupformEntity> param) {
                return new ListCell<GroupformEntity>() {
                    @Override
                    public void updateItem(GroupformEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getName());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillQualificationLevel() {
        comboBox.setItems(FXCollections.observableArrayList(new QualificationLevelController().getAllQualificationLevel()));
        comboBox.setCellFactory(new Callback<ListView<QualificationlevelEntity>, ListCell<QualificationlevelEntity>>() {

            @Override
            public ListCell<QualificationlevelEntity> call(ListView<QualificationlevelEntity> param) {
                return new ListCell<QualificationlevelEntity>() {
                    @Override
                    public void updateItem(QualificationlevelEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getName());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillDiplomaType(DiplomaformEntity form) {
        comboBox.setItems(FXCollections.observableArrayList(new TypeController().getAllTypeByForm(form)));
        comboBox.setCellFactory(new Callback<ListView<DiplomatypeEntity>, ListCell<DiplomatypeEntity>>() {
            @Override
            public ListCell<DiplomatypeEntity> call(ListView<DiplomatypeEntity> param) {
                return new ListCell<DiplomatypeEntity>() {
                    @Override
                    public void updateItem(DiplomatypeEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getName());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillDiplomaForm(QualificationlevelEntity qualification) {
        comboBox.setItems(FXCollections.observableArrayList(new FormController().getAllFormByQualification(qualification)));
        comboBox.setCellFactory(new Callback<ListView<DiplomaformEntity>, ListCell<DiplomaformEntity>>() {
            @Override
            public ListCell<DiplomaformEntity> call(ListView<DiplomaformEntity> param) {
                return new ListCell<DiplomaformEntity>() {
                    @Override
                    public void updateItem(DiplomaformEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getName());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillTeacherType() {
        comboBox.setItems(FXCollections.observableArrayList(new TypeOwnerMarkController().getAllType()));
        comboBox.setCellFactory(new Callback<ListView<TypeOwnerMarkEntity>, ListCell<TypeOwnerMarkEntity>>() {

            @Override
            public ListCell<TypeOwnerMarkEntity> call(ListView<TypeOwnerMarkEntity> param) {
                return new ListCell<TypeOwnerMarkEntity>() {
                    @Override
                    public void updateItem(TypeOwnerMarkEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getType());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillRole() {
        comboBox.setItems(FXCollections.observableArrayList(new RoleController().getAllRole()));
        comboBox.setCellFactory(new Callback<ListView<UserroleEntity>, ListCell<UserroleEntity>>() {

            @Override
            public ListCell<UserroleEntity> call(ListView<UserroleEntity> param) {
                return new ListCell<UserroleEntity>() {
                    @Override
                    public void updateItem(UserroleEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getName());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillCurator() {
        comboBox.setItems(FXCollections.observableArrayList(new TeacherController().getCurator()));
        comboBox.setCellFactory(new Callback<ListView<TeachersEntity>, ListCell<TeachersEntity>>() {

            @Override
            public ListCell<TeachersEntity> call(ListView<TeachersEntity> param) {
                return new ListCell<TeachersEntity>() {
                    @Override
                    public void updateItem(TeachersEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getLastName() + " "
                                    + item.getFirstName().charAt(0)
                                    + ". " + item.getMiddleName().charAt(0) + ".");
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void fillGroupType() {
        comboBox.setItems(FXCollections.observableArrayList(new GroupTypeController().getAllGroupType()));
        comboBox.setCellFactory(new Callback<ListView<GrouptypeEntity>, ListCell<GrouptypeEntity>>() {

            @Override
            public ListCell<GrouptypeEntity> call(ListView<GrouptypeEntity> param) {
                return new ListCell<GrouptypeEntity>() {
                    @Override
                    public void updateItem(GrouptypeEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            setText(item.getName());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
}
