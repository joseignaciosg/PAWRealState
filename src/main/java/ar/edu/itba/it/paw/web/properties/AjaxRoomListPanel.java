package ar.edu.itba.it.paw.web.properties;

import java.io.*;
import java.util.*;

import org.apache.wicket.ajax.*;
import org.apache.wicket.ajax.markup.html.*;
import org.apache.wicket.markup.html.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.model.util.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

@SuppressWarnings("serial")
public class AjaxRoomListPanel extends Panel {

	private ListView<RoomRepresentation> listView;

	private List<RoomRepresentation> room;

	public List<Room> getRooms() {
		final List<Room> _rooms = new ArrayList<Room>();

		for (final RoomRepresentation _room : this.room) {
			_rooms.add(_room.getRoom());
		}

		return _rooms;
	}

	private List<RoomRepresentation> getRoomRepresentations(
			final List<Room> rooms) {
		final List<RoomRepresentation> _rooms = new ArrayList<RoomRepresentation>();

		for (final Room room : rooms) {
			_rooms.add(new RoomRepresentation(room));
		}

		return _rooms;
	}

	public AjaxRoomListPanel(final String id, final Form<?> form) {
		this(id, form, new ArrayList<Room>());
	}

	@SuppressWarnings("serial")
	public AjaxRoomListPanel(final String id, final Form<?> form,
			final List<Room> rooms) {
		super(id);

		final WebMarkupContainer rooms_container = new WebMarkupContainer(
				"rooms_container");

		rooms_container.setOutputMarkupId(true);

		this.room = this.getRoomRepresentations(rooms);

		this.listView = new ListView<RoomRepresentation>("room",
				new ListModel<RoomRepresentation>(this.room)) {

			@Override
			protected void populateItem(final ListItem<RoomRepresentation> item) {

				item.add(new DropDownChoice<RoomType>("type",
						new PropertyModel<RoomType>(item.getModel(), "type"),
						Arrays.asList(RoomType.values()),
						new EnumChoiceRenderer<RoomType>()));
				item.add(new TextField<Integer>("width",
						new PropertyModel<Integer>(item.getModel(), "width")));
				item.add(new TextField<Integer>("height",
						new PropertyModel<Integer>(item.getModel(), "height")));

				item.add(new AjaxLink<Void>("remove_item") {

					@Override
					public void onClick(final AjaxRequestTarget target) {

						AjaxRoomListPanel.this.listView.getModelObject()
								.remove(item.getModelObject());

						target.add(rooms_container);
					}
				});
			}
		}.setReuseItems(true);

		this.setOutputMarkupId(true);
		this.listView.setOutputMarkupId(true);

		final AjaxLink<Void> ajaxLink = new AjaxLink<Void>("add_item") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				target.add(rooms_container);
				AjaxRoomListPanel.this.listView.getModelObject().add(
						new RoomRepresentation(RoomType.BATHROOM, 1, 1));
			}
		};

		rooms_container.add(ajaxLink);
		rooms_container.add(this.listView);
		this.add(rooms_container);
	}

	private class RoomRepresentation implements Serializable {
		private Integer id;

		private RoomType type;
		private Integer width;
		private Integer height;

		public RoomRepresentation(final Room room) {
			this(room.getType(), room.getWidth(), room.getHeight());
			this.id = room.getId();
		}

		public RoomRepresentation(final RoomType type, final Integer width,
				final Integer height) {
			this.type = type;
			this.width = width;
			this.height = height;
		}

		public Room getRoom() {
			if (this.id == null) {
				return new Room(this.type, this.width, this.height);
			} else {
				final Room room = new EntityModel<Room>(Room.class, this.id)
						.getObject();
				room.setType(this.type);
				room.setWidth(this.width);
				room.setHeight(this.height);
				return room;
			}
		}
	}

}
