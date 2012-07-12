package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.base.*;

@SuppressWarnings("serial")
public class PhotoEditPage extends BasePage {

	@SpringBean
	HibernatePropertyRepository properties;

	IModel<Property> model;

	private transient List<FileUpload> photo_input;

	public PhotoEditPage(final IModel<Property> model) {
		this.model = model;

		this.add(new RefreshingView<Photo>("photo_item") {
			@Override
			protected Iterator<IModel<Photo>> getItemModels() {
				final List<IModel<Photo>> result = new ArrayList<IModel<Photo>>();
				model.detach();
				for (final Photo photo : model.getObject().getPhotos()) {
					result.add(new EntityModel<Photo>(Photo.class, photo));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<Photo> item) {
				final PageParameters params = new PageParameters();
				params.add("id", item.getModelObject().getId());
				item.add(new Image("photo", RealStateApp.imageReference, params));
				item.add(new Link<Void>("photo_delete") {
					@Override
					public void onClick() {
						model.detach();
						model.getObject().removePhoto(item.getModelObject());
					}
				});
			}
		});

		this.loadForm();
	}

	private void loadForm() {
		final Form<PhotoEditPage> f = new Form<PhotoEditPage>("photo_form",
				new CompoundPropertyModel<PhotoEditPage>(this));

		f.add(new FileUploadField("photo_input").setRequired(true));
		f.add(new Button("submit_button") {
			@Override
			public void onSubmit() {

				final FileUpload upload = PhotoEditPage.this.photo_input.get(0);

				PhotoEditPage.this.model.detach();

				final Property prop = PhotoEditPage.this.model.getObject();

				prop.addPhoto(new Photo(upload.getBytes(), upload
						.getContentType(), prop));

			}
		}.add(new AttributeModifier("value", Model.of(this
				.getString("add_photo")))));

		this.add(f);
	}
}
