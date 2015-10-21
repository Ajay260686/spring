package com.apress.prospring3;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch10.domain.Contact;
import com.apress.prospring3.ch10.domain.ContactTelDetail;
import com.apress.prospring3.ch10.domain.Hobby;
import com.apress.prospring3.ch10.service.ContactService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JPAPersistenceTests {

	@Autowired
	private ContactService contactService;

	@Test
	@Transactional
	public void findAll() throws Exception {
		List<Contact> contacts = contactService.findAll();
		listContacts(contacts);
	}

	@Test
	@Transactional
	public void findAllWithDetail() throws Exception {
		List<Contact> contacts = contactService.findAllWithDetail();
		listContactsWithDetail(contacts);
	}

	@Test
	@Transactional
	public void findById() throws Exception {
		Contact contact = contactService.findById(1L);
		System.out.println("Contact found by id :" + contact.getFirstName() + " " + contact.getLastName());
	}

	@Test
	@Transactional
	public void saveContact() throws Exception {
		Contact contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "1111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactService.save(contact);
		List<Contact> contacts = contactService.findAllWithDetail();
		listContactsWithDetail(contacts);

	}

	@Test
	@Transactional
	public void updateContact() throws Exception {
		// Find contact by ID
		Contact contact = contactService.findById(1l);
		System.out.println("");
		System.out.println("Contact with id 1:" + contact);
		System.out.println("");
		// Update contact
		contact.setFirstName("Kim Fung");
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTel = null;
		for (ContactTelDetail contactTel : contactTels) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contactTels.remove(toDeleteContactTel);
		contactService.save(contact);
		List<Contact> contacts = contactService.findAllWithDetail();
		listContactsWithDetail(contacts);

	}

	@Test
	@Transactional
	public void deleteContact() throws Exception {
		Contact contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "1111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactService.save(contact);
		List<Contact> contacts = contactService.findAllWithDetail();
		listContactsWithDetail(contacts);
		contact = contactService.findById(1l);
		contactService.delete(contact);
		contacts = contactService.findAllWithDetail();
		listContactsWithDetail(contacts);

	}

	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}

	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts with details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}
			if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies()) {
					System.out.println(hobby);
				}
			}
			System.out.println();
		}
	}

}
