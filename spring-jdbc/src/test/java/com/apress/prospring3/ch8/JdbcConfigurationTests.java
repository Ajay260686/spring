package com.apress.prospring3.ch8;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;
import com.apress.prospring3.ch8.domain.ContactTelDetail;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcConfigurationTests {

	@Autowired
	private ContactDao contactDao;

	//@Test
	public void testSimpleProperties() throws Exception {
		assertNotNull(contactDao);
		System.out.println("First name for contact id 2 is: " + contactDao.findFirstNameById(2l));
		System.out.println("Last name for contact id 2 is: " + contactDao.findLastNameById(2l));
	}

	//@Test
	public void testFindAll() throws Exception {
		assertNotNull(contactDao);
		List<Contact> contacts = contactDao.findAll();
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
	}

	//@Test
	public void testFindAllWithDetail() throws Exception {
		assertNotNull(contactDao);
		List<Contact> contacts = contactDao.findAllWithDetail();
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
	}

	@Test
	public void testInsertWithDetail() throws Exception {
		// Insert contact with details
		Contact contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date((new GregorianCalendar(1964, 10, 1)).getTime().getTime()));
		List<ContactTelDetail> contactTelDetails = new ArrayList<ContactTelDetail>();
		ContactTelDetail contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Home");
		contactTelDetail.setTelNumber("11111111");
		contactTelDetails.add(contactTelDetail);
		contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Mobile");
		contactTelDetail.setTelNumber("22222222");
		contactTelDetails.add(contactTelDetail);
		contact.setContactTelDetails(contactTelDetails);
		contactDao.insertWithDetail(contact);
		List<Contact> contacts = contactDao.findAllWithDetail();
		listContacts(contacts);

	}

	private static void listContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
	}

}
